package com.microservice.payment.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.payment.entity.Payment;
import com.microservice.payment.repository.PaymentRepository;
import com.microservie.payment.exceptions.NotFoundException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));
    }

    @Override
    public Payment addPayment(@Valid Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}
