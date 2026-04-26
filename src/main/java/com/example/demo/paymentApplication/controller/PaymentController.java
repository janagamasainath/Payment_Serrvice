package com.example.demo.paymentApplication.controller;

import com.example.demo.paymentApplication.model.Payment;
import com.example.demo.paymentApplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/dopayment")
    public Payment doPayment(@RequestBody Payment payment) {
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) {
        return paymentService.findPaymentHistoryByOrderId(orderId);

    }
    @GetMapping("/getall")
    public List<Payment> findallPayemantHistory(){
        return paymentService.findallPayemantHistory();
    }

    @GetMapping("/{transactionId}")
    public Payment getByTransactionId(@PathVariable String transactionId){
        Payment byTransactionId = paymentService.getByTransactionId(transactionId);
        return byTransactionId;

    }

    @GetMapping("status/{paymentStatus}")
    public List<Payment> statusReport(@PathVariable String paymentStatus) {
        List<Payment> payments = paymentService.statusReport(paymentStatus);
        return payments;
    }
}
