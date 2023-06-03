package com.example.vehiclesrent.builder;

import com.example.vehiclesrent.model.Car;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarMock {

    private static final String BRAND = "BMW";
    private static final String MODEL = "X6";

    private static Car getCarBuilderWithNameAndBrand() {
        var car = new Car();
        car.setBrand(BRAND);
        car.setModel(MODEL);
        return car;
    }

    public static Car getCarToSave() {
        return getCarBuilderWithNameAndBrand();
    }

    public static Car getSavedCarWithRandomIdAndCreatedDate() {
        var car = getCarBuilderWithNameAndBrand();
        car.setUuid(UUID.randomUUID());
        //car.setCreatedAt(LocalDateTime.now());
        return car;
    }
}
