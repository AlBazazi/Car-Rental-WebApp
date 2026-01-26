package com.carrental.CarRentalApp.factory;

import com.carrental.CarRentalApp.model.Car;
import org.springframework.stereotype.Component;

@Component
public class Mercedes implements CarFactory {
    @Override
    public Car createCar(String model, int quantity, boolean available, int price) {
        return new Car("Mercedes", model, quantity, available, price);
    }
}
