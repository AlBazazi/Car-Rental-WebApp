package com.carrental.CarRentalApp.factory;
import com.carrental.CarRentalApp.model.Car;
import org.springframework.stereotype.Component;

@Component
public class Audi implements CarFactory {
    @Override
    public Car createCar(String model, int quantity, boolean available, int price) {
        return new Car("Audi", model, quantity, available, price);
    }
}
