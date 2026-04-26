package com.example.demo.paymentApplication.repo;

import com.example.demo.paymentApplication.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(int orderId);

    @Query("SELECT p FROM Payment p WHERE p.transactionId = :transactionId")
    Payment getByTransactionId(@Param("transactionId") String transactionId);

    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = :paymentStatus")
    List<Payment> statusReport(@Param("paymentStatus") String paymentStatus);
}
