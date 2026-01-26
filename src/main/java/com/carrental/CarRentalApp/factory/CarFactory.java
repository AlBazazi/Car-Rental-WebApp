// Factory Pattern
package com.carrental.CarRentalApp.factory;
import com.carrental.CarRentalApp.model.Car;

public interface CarFactory {
    Car createCar(String model, int quantity, boolean available, int price);
}

