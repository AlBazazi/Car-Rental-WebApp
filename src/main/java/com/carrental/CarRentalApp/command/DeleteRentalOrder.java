package com.carrental.CarRentalApp.command;

import com.carrental.CarRentalApp.repository.RentalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteRentalOrder implements Execution {
    private final RentalOrderRepository rentalOrderRepository;
    private Long id;

    @Autowired
    public DeleteRentalOrder(RentalOrderRepository rentalOrderRepository) {
        this.rentalOrderRepository = rentalOrderRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        if (id != null) {
            rentalOrderRepository.deleteById(id);
        }
    }

    public RentalOrderRepository getRentalOrderRepository() {
        return this.rentalOrderRepository;
    }
}
