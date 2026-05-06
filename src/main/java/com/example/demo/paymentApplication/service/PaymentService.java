package com.example.demo.paymentApplication.service;


import com.example.demo.paymentApplication.model.Payment;
import com.example.demo.paymentApplication.repo.PaymentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
        payment.setPaymentStatus(paymentStatus());
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

    public Payment getByTransactionId(String transactionId){
        Payment byTransactionId = paymentRepo.getByTransactionId(transactionId);
        return byTransactionId;
    }

    public List<Payment> statusReport(String status){
        List<Payment> payments = paymentRepo.statusReport(status);
        return payments;
    }

    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void consume(Payment event) {
        System.out.println("Received Order: " + event);

        // Simulate payment processing
        if (event.getPaymentStatus().equals("Success")) {
            System.out.println("Payment SUCCESS for Order: " + event.getOrderId());
        } else {
            System.out.println("Payment FAILED");
        }
    }
}
