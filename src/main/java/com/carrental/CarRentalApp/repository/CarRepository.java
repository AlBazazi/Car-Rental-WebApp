package com.carrental.CarRentalApp.repository;
import com.carrental.CarRentalApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByMakeAndModel(String make, String model);
    List<Car> findByMakeAndQuantityGreaterThanAndAvailableIsTrue(String make, int quantity);
    @Query("SELECT c.price FROM Car c WHERE c.make = :make AND c.model = :model")
    Double findPriceByMakeAndModel(String make, String model);

    Car findFirstByMakeAndModel(String state, String model);
}
