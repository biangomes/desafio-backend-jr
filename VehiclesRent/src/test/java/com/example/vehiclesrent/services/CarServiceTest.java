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


    @DisplayName("Must return a saved car")
    @Test
    public void must_save_one_car() {
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

        List<Car> cars = List.of(car1, car2);

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> foundCars = carService.getAll();

        assertNotNull(foundCars);
        assertEquals(2, foundCars.size());


        verify(carRepository).findAll();
    }


    @DisplayName("Must return one car by id from database")
    @Test
    public void must_return_one_car_by_id() {
        var carInDatabase = CarMock.getSavedCarWithRandomIdAndCreatedDate();
        UUID uuid = carInDatabase.getUuid();
        var optionalFromCarInDatabase = Optional.of(carInDatabase);

        when(carRepository.findById(uuid)).thenReturn(optionalFromCarInDatabase);

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

        carRepository.save(carInDatabase);

        // Act
        carService.delete(uuid);

        // Assert
        verify(carRepository, times(1)).findById(uuid);
        verify(carRepository, times(1)).delete(uuid);

    }
}
