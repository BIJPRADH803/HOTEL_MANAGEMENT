package com.microservice.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.booking.entity.Booking;



public interface BookingRepository extends MongoRepository<Booking, String> {
}
