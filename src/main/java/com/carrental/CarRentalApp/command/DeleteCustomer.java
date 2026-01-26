package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomer implements Execution {
    private final CustomerRepository customerRepository;
    private Long id;

    @Autowired
    public DeleteCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        if (id != null) {
            customerRepository.deleteById(id);
        }
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
