package com.carrental.CarRentalApp.service;
import com.carrental.CarRentalApp.builder.RentalOrderBuilder;
import com.carrental.CarRentalApp.model.*;
import com.carrental.CarRentalApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalOrderService {
    @Autowired private RentalOrderRepository rentalOrderRepository;
    @Autowired private CarRepository carRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private RentalOrderBuilder rentalOrderBuilder;
    @Autowired private CarService carService;

    public RentalOrder createRentalOrder(String name,
                                         String email,
                                         String phoneNumber, String nic,
                                         String make, String model,
                                         int numberOfDays,
                                         LocalDate startDate) {
        // Find or create customer
        Customer customer = customerRepository.findByNic(nic)
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setName(name);
                    newCustomer.setPhoneNumber(phoneNumber);
                    newCustomer.setNic(nic);
                    newCustomer.setEmail(email);
                    return customerRepository.save(newCustomer);
                });

        // Find Car by make + model
        Optional<Car> carOptional = carRepository.findByMakeAndModel(make, model);
        if (carOptional.isEmpty()) {
            System.out.println("Error in RentalOrderService, CarOptional is EMPTY");
            throw new IllegalArgumentException("Car not found for make: " + make + " and model: " + model);
        }
        Car car = carOptional.get();
        if (car.getQuantity() <= 0 || !car.isAvailable()) {
            System.out.println("Error in RentalOrderService, car.getQuantity editing");
            throw new IllegalStateException("Car is unavailable.");
        }

        // Create order
        rentalOrderBuilder.setCustomer(customer);
        rentalOrderBuilder.setCar(car);
        rentalOrderBuilder.setNumberOfDays(numberOfDays);
        rentalOrderBuilder.setStartDate(startDate);
        rentalOrderBuilder.setEmail(email);
        RentalOrder order = rentalOrderBuilder.build();

        // Implementing Cost
        order.setTotalPrice(
                BigDecimal.valueOf((long) order.getCar().getPrice()*numberOfDays)
        );
        order.setId(null);

        car.setQuantity(car.getQuantity()-1);
        carRepository.save(car);
        return rentalOrderRepository.save(order);
    }

    public List<String> findByMakeAndQuantityGreaterThanAndAvailableIsTrue(String make) {
        List<Car> availableCars = carRepository.findByMakeAndQuantityGreaterThanAndAvailableIsTrue(make, 0);
        return availableCars.stream()
                .map(Car::getModel)
                .distinct()
                .collect(Collectors.toList());
    }
    public Optional<RentalOrder> getById(Long id) {
        return rentalOrderRepository.findById(id);
    }
    @Transactional
    public void cleanUpAfterFailedEmail(RentalOrder order) {
        carService.updateQuantity(order.getId());
        rentalOrderRepository.delete(order);
        Customer customer = order.getCustomer();
        if (customer != null && customer.getId() != null) {
            customerRepository.delete(customer);
        }
    }
}
