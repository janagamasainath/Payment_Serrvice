package com.example.demo.paymentApplication.service;


import com.example.demo.paymentApplication.model.Payment;
import com.example.demo.paymentApplication.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment doPayment(Payment payment) {
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymetStatus(paymentStatus());
       return paymentRepo.save(payment);
    }

    public String paymentStatus(){
        return new Random().nextBoolean() ? "Success" : "Failure";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) {
        return paymentRepo.findByOrderId(orderId);
    }

    public List<Payment> findallPayemantHistory() {
        return paymentRepo.findAll();
    }
}
