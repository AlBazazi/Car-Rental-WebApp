package com.carrental.CarRentalApp.repository;
import com.carrental.CarRentalApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNic(String nic);
}
