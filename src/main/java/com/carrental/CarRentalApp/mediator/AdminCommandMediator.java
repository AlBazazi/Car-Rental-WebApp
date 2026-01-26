package com.carrental.CarRentalApp.mediator;

import com.carrental.CarRentalApp.command.*;
import com.carrental.CarRentalApp.model.*;
import com.carrental.CarRentalApp.observer.AdminObserver;
import com.carrental.CarRentalApp.repository.PaymentRepository;
import com.carrental.CarRentalApp.repository.RentalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdminCommandMediator implements AdminMediator {

    @Autowired private RentalOrderRepository rentalOrderRepository;
    @Autowired private PaymentRepository paymentRepository;

    @Autowired private AddCar addCar;
    @Autowired private DeleteCar deleteCar;
    @Autowired private AddCustomer addCustomer;
    @Autowired private DeleteCustomer deleteCustomer;
    @Autowired private SaveRentalOrder saveRentalOrder;
    @Autowired private DeleteRentalOrder deleteRentalOrder;
    @Autowired private AdminObserver adminObserver;
    @Autowired private DeletePayment deletePayment;

    @Override
    public void saveCar(Car car) {
        addCar.setCar(car);
        CommandExecutor.execute(addCar);
        adminObserver.update(car);
    }

    @Override
    public void deleteCar(Long id) {
        deleteCar.setId(id);
        CommandExecutor.execute(deleteCar);
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        return addCar.getCarRepository().findById(id);
    }

    @Override
    public List<Car> getAllCars() {
        return addCar.getCarRepository().findAll();
    }

    @Override
    public void deletePayment(Long id) {
        deletePayment.setId(id);
        CommandExecutor.execute(deletePayment);
    }

    @Override
    public void saveCustomer(Customer customer) {
        addCustomer.setCustomer(customer);
        CommandExecutor.execute(addCustomer);
        adminObserver.update(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> ocustomer = deleteCustomer.getCustomerRepository().findById(id);
        Customer customer = ocustomer.get();
        if (rentalOrderRepository.existsByCustomerId(id)) {
            throw new IllegalStateException("To remove the customer, you must first remove their rental orders.");
        }
        deleteCustomer.setId(id);
        CommandExecutor.execute(deleteCustomer);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return addCustomer.getCustomerRepository().findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return addCustomer.getCustomerRepository().findAll();
    }

    @Override
    public void saveOrder(RentalOrder order) {
        saveRentalOrder.setOrder(order);
        CommandExecutor.execute(saveRentalOrder);
        adminObserver.update(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (paymentRepository.existsByRentalOrderId(id)) {
            throw new IllegalStateException("To remove the rental order, you must first remove the payment.");
        }
        deleteRentalOrder.setId(id);
        CommandExecutor.execute(deleteRentalOrder);
    }

    @Override
    public Optional<RentalOrder> findOrderById(Long id) {
        return saveRentalOrder.getRentalOrderRepository().findById(id);
    }

    @Override
    public List<RentalOrder> getAllOrders() {
        return saveRentalOrder.getRentalOrderRepository().findAll();
    }

    @Override
    public void updateObserver(Object entity) {
        adminObserver.update(entity);
    }
}
