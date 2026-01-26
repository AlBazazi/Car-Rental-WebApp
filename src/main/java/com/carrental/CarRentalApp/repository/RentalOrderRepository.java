package com.carrental.CarRentalApp.repository;
import com.carrental.CarRentalApp.model.RentalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalOrderRepository extends JpaRepository<RentalOrder, Long> {
    boolean existsByCustomerId(Long id);
}
