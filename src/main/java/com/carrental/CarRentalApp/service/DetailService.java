package com.carrental.CarRentalApp.service;
import com.carrental.CarRentalApp.model.Details;
import com.carrental.CarRentalApp.model.RentalOrder;
import com.carrental.CarRentalApp.repository.RentalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Autowired private RentalOrderRepository rentalOrderRepository;

    public Object getRentalDetailsByOrderId(Long orderId) {
        RentalOrder rentalOrder = rentalOrderRepository.findById(orderId).orElse(null);
        if (rentalOrder != null) {
            return new Details(
                    rentalOrder.getCustomer().getName(),
                    rentalOrder.getCustomer().getEmail(),
                    rentalOrder.getCar().getMake(),
                    rentalOrder.getCar().getModel(),
                    rentalOrder.getStartDate(),
                    rentalOrder.getNumberOfDays(),
                    rentalOrder.getTotalPrice()
            );
        }
        return "No such Order Found!";
    }
}
