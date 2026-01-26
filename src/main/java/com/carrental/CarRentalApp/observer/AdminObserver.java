package com.carrental.CarRentalApp.observer;

import com.carrental.CarRentalApp.model.Car;
import com.carrental.CarRentalApp.model.Customer;
import com.carrental.CarRentalApp.model.RentalOrder;
import org.springframework.stereotype.Component;

@Component
public class AdminObserver implements RentalObserver {

    @Override
    public void update(Object entity) {
        if (entity instanceof RentalOrder) {
            RentalOrder order = (RentalOrder) entity;
            System.out.println("Observer: RentalOrder updated: " + order);
        } else if (entity instanceof Car) {
            Car car = (Car) entity;
            System.out.println("Observer: Car updated: " + car);
        } else if (entity instanceof Customer) {
            Customer customer = (Customer) entity;
            System.out.println("Observer: Customer updated: " + customer);
        } else {
            System.out.println("Observer: Unknown entity updated: " + entity);
        }
    }
}
