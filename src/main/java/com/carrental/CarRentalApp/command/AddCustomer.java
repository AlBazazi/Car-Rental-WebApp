package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.model.Customer;
import com.carrental.CarRentalApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCustomer implements Execution {
    private final CustomerRepository customerRepository;
    private Customer customer;

    @Autowired
    public AddCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCustomer(Customer customer) {this.customer=customer;}

    @Override
    public void execute() {
        if (customer != null) {
            customerRepository.save(customer);
        }
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
