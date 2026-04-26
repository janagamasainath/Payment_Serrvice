package com.example.demo.paymentApplication.repo;

import com.example.demo.paymentApplication.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(int orderId);
}
