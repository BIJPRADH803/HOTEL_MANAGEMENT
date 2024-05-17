package com.microservice.payment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    @NotEmpty(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotEmpty(message = "Payment gateway is required")
    private String paymentGateway;

    public Payment() {
    }

    public Payment(String transactionId, Double amount, String paymentGateway) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentGateway = paymentGateway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
}
