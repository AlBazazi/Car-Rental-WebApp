// Builder Pattern

package com.carrental.CarRentalApp.builder;
import com.carrental.CarRentalApp.model.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class RentalOrderBuilder {
    private final RentalOrder rentalOrder;
    public RentalOrderBuilder() {
        rentalOrder = new RentalOrder();
    }
    public RentalOrderBuilder setCar(Car car) {
        rentalOrder.setCar(car);
        return this;
    }
    public RentalOrderBuilder setCustomer(Customer customer) {
        rentalOrder.setCustomer(customer);
        return this;
    }
    public RentalOrderBuilder setNumberOfDays(int numberOfDays) {
        rentalOrder.setNumberOfDays(numberOfDays);
        return this;
    }
    public RentalOrderBuilder setStartDate(LocalDate startDate) {
        rentalOrder.setStartDate(startDate);
        return this;
    }
    public RentalOrderBuilder setEmail(String email) {
        rentalOrder.setEmail(email);
        return this;
    }
    public RentalOrder build() {
        return rentalOrder;
    }
}
