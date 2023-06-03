package com.example.vehiclesrent.services;

import com.example.vehiclesrent.model.Car;
import com.example.vehiclesrent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService implements ICrudService<Car> {

    // TODO A variável carRepository deve ser final para que não seja alterada depois de injetada.
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getById(UUID uuid) {
        // TODO Aqui tu pode retornar um Optional<Car> direto.
        // Retornar um null como na linha 43 pode causar um NullPointerException para quem chamar esse método.
        // Se retornar um Optional, tu pode retornar um Optional.empty() caso não encontre o registro.
        return carRepository.findById(uuid);
    }

    @Override
    public Car save(Car object) {
        return carRepository.save(object);
    }

    @Override
    public void delete(UUID uuid) {
        Optional<Car> record = carRepository.findById(uuid);
        // TODO A classe Optional oferece o método ifPresent que recebe um Consumer como parâmetro,
        //  então tu pode chamar direto o método delete do carRepository.
        record.ifPresent(car -> carRepository.delete(record.get().getUuid()));
    }
}
