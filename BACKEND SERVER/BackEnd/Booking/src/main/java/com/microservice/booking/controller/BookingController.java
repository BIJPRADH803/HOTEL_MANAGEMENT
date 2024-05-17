package com.microservice.booking.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.booking.entity.Booking;
import com.microservice.booking.exceptions.NotFoundException;
import com.microservice.booking.service.BookingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	/*
	  author:Bijay Kuamr 
	 */

	
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
    	logger.info("Getting list of Booking");
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        try {
        	logger.info("Getting list of Booking by ID");
            Booking booking = bookingService.getBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking) 
    {
    	logger.info("Add Booking");
        Booking createdBooking = bookingService.addBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @Valid @RequestBody Booking booking) {
        try {
        	logger.info("Updating Booking details");
            Booking updatedBooking = bookingService.updateBooking(id, booking);
            return ResponseEntity.ok(updatedBooking);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        try {
        	logger.info("Deleting the booking ");
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


