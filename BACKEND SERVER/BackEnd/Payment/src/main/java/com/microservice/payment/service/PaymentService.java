package com.microservice.payment.service;

import com.microservice.payment.entity.Payment;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();

    Payment getPaymentById(String id);

    Payment addPayment(@Valid Payment payment);

    void deletePayment(String id);
}
