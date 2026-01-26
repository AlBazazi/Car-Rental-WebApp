package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.model.RentalOrder;
import com.carrental.CarRentalApp.repository.RentalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRentalOrder implements Execution {
    private final RentalOrderRepository rentalOrderRepository;
    private RentalOrder order;

    @Autowired
    public SaveRentalOrder(RentalOrderRepository rentalOrderRepository) {
        this.rentalOrderRepository = rentalOrderRepository;
    }

    public void setOrder(RentalOrder order) {
        this.order = order;
    }

    @Override
    public void execute() {
        if (order != null) {
            rentalOrderRepository.save(order);
        }
    }

    public RentalOrderRepository getRentalOrderRepository() {
        return rentalOrderRepository;
    }
}
