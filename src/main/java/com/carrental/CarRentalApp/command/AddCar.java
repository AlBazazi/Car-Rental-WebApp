package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.model.Car;
import com.carrental.CarRentalApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCar implements Execution {
    private final CarRepository carRepository;
    private Car car;

    @Autowired
    public AddCar(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void setCar(Car car) {this.car=car;}

    @Override
    public void execute() {
        if (car != null) {
            carRepository.save(car);
        }
    }
    public CarRepository getCarRepository() {
        return carRepository;
    }
}
