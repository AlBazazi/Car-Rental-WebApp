package com.carrental.CarRentalApp.repository;

import com.carrental.CarRentalApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByRentalOrderId(Long id);
}
