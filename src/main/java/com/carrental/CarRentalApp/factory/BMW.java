package com.carrental.CarRentalApp.factory;
import com.carrental.CarRentalApp.model.Car;
import org.springframework.stereotype.Component;

@Component
public class BMW implements CarFactory {
    Car car;
    @Override
    public Car createCar(String model, int quantity, boolean available, int price) {
        return new Car("BMW", model, quantity, available, price);
    }
}
