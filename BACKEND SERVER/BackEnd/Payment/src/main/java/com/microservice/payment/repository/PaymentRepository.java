package com.microservice.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.payment.entity.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
}
