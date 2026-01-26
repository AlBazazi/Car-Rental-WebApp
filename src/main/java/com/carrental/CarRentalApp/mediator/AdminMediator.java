package com.carrental.CarRentalApp.mediator;
import com.carrental.CarRentalApp.model.Car;
import com.carrental.CarRentalApp.model.Customer;
import com.carrental.CarRentalApp.model.Payment;
import com.carrental.CarRentalApp.model.RentalOrder;
import java.util.List;
import java.util.Optional;

public interface AdminMediator {
    void saveCar(Car car);
    void deleteCar(Long id);
    Optional<Car> findCarById(Long id);
    List<Car> getAllCars();

    void saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    Optional<Customer> findCustomerById(Long id);
    List<Customer> getAllCustomers();

    void saveOrder(RentalOrder order);
    void deleteOrder(Long id);
    Optional<RentalOrder> findOrderById(Long id);
    List<RentalOrder> getAllOrders();

    void deletePayment(Long id);

    void updateObserver(Object entity);
}
