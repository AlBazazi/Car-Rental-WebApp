package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCar implements Execution {
    private final CarRepository carRepository;
    private Long id;

    @Autowired
    public DeleteCar(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void execute() {
        if (id != null) {
            carRepository.deleteById(id);
        }
    }

    public void setId(Long id) {this.id=id;}

    public CarRepository getCarRepository() {
        return carRepository;
    }
}
