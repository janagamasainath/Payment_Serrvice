package com.example.demo.paymentApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "PAYMENT_APPLICATION")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymetId;
    private String transactionId;
    private String paymetStatus;

    private Integer orderId; // comes from order servide
    private Double amount; // comes from orders service

}
