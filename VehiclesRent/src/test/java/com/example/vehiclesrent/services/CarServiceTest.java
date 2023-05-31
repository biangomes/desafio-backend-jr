package com.example.vehiclesrent.services;

import com.example.vehiclesrent.builder.CarMock;
import com.example.vehiclesrent.model.Car;
import com.example.vehiclesrent.repository.CarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    // O ideal é não utilizar essa lista, ela pode inclusive ser removida.
    //private final List<Car> cars = new ArrayList<>();


    @DisplayName("Must return a saved car")
    @Test
    public void must_save_one_car() {
        // TODO Aqui tu pode preferir usar o construtor de Car para criar o objeto.
        // Também é possível tu criar um Builder para a classe Car e usar ele para criar o objeto,
        // isso te ajuda a deixar o código mais legível, fácil de manter e elimina código duplicado.
        var carToSave = CarMock.getCarToSave();
        var carToReturnFromDatabase = CarMock.getSavedCarWithRandomIdAndCreatedDate();

        when(carRepository.save(carToSave)).thenReturn(carToReturnFromDatabase);

        var savedCar = carService.save(carToSave);

        assertThat(savedCar)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(carToReturnFromDatabase);

    }


    @DisplayName("Must return a list of cars from database")
    @Test
    public void must_return_list_of_cars() {

        var car1 = CarMock.getSavedCarWithRandomIdAndCreatedDate();
        var car2 = CarMock.getSavedCarWithRandomIdAndCreatedDate();


        // Não é recomendável usar a lista que foi criada fora do método, ela pode trazer ou criar sujeira
        // para os outros testes, o ideal é criar uma lista dentro do método e adicionar os objetos que tu quer.
        List<Car> cars = List.of(car1, car2);

        when(carRepository.findAll()).thenReturn(cars);

        // Aqui tu não pode chamar direto a repository, tu tem que chamar o service.
        // Tu chama a repository se estiver testando a repository, mas aqui tu está testando o service,
        // então tu faz o mock da repository e chama o service. Pra dar mais contexto, se tu chamar a repository aqui
        // o teu teste fica sem efeito porque tu fizesse um mock e em seguida testasse ele.
        // É como fazer assim:
        // when(carRepository.findAll()).thenReturn(this.cars);
        // List<Car> foundCars = carRepository.findAll();


        // O correto seria: List<Car> foundCars = carService.findAll();
        List<Car> foundCars = carRepository.findAll();

        assertNotNull(foundCars);
        assertEquals(2, foundCars.size());

        // Aqui tu pode usar o verify para verificar se o método foi chamado sem usar o times(1)
        // porque ele fica implícito.
        verify(carRepository).findAll(); // É igual ao verify abaixo.
        verify(carRepository, times(1)).findAll();

    }


    @DisplayName("Must return one car by id from database")
    @Test
    public void must_return_one_car_by_id() {
        var carInDatabase = CarMock.getSavedCarWithRandomIdAndCreatedDate();
        UUID uuid = carInDatabase.getUuid();
        var optionalFromCarInDatabase = Optional.of(carInDatabase);

        when(carRepository.findById(uuid)).thenReturn(optionalFromCarInDatabase);

        // Aqui a chamada também deve ser pro carService e não pro carRepository.
        // carRepository.save(carToSave);

        Optional<Car> carFromDatabase = carService.getById(uuid);


        assertThat(carFromDatabase).isNotEmpty();

        assertThat(carFromDatabase.get())
                .usingRecursiveComparison()
                .isEqualTo(carInDatabase);
    }


    @DisplayName("Must delete a car given its ID")
    @Test
    public void must_delete_one_car() {
        // Arrange
        var carInDatabase = CarMock.getSavedCarWithRandomIdAndCreatedDate();
        UUID uuid = carInDatabase.getUuid();

        when(carRepository.findById(uuid)).thenReturn(Optional.of(carInDatabase));

        // Aqui tu pode salvar o carro pelo carRepository porque não é o que está sendo testado
        // e ele precisa estar no banco de dados para ser deletado,
        // mas deve deletar ele pelo carService porque é ele que está sendo testado.
        //carRepository.save(car1);
        //carRepository.delete(car1.getUuid());

        // Act
        carService.delete(uuid);

        // Assert
        verify(carRepository, times(1)).findById(uuid);
        verify(carRepository, times(1)).delete(uuid);

    }
}
