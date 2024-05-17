package com.microservice.booking.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.microservice.booking.entity.Booking;
import com.microservice.booking.exceptions.NotFoundException;
import com.microservice.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
   
    @Override
    public List<Booking> getAllBookings() {
    	logger.info("Getting Booking list");
    	logger.info(" Successfull search of all booking");
        return bookingRepository.findAll();
        
        
        
    }

    @Override
    public Booking getBookingById(String id) {
    	logger.info("Getting booking by id");
    	logger.info(" Successfully booking by id");
    	return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found with id: " + id));
    }

    @Override
    public Booking addBooking(Booking booking) {
    	logger.info("Adding Booking");
        Assert.notNull(booking, "Booking cannot be null");
        logger.info("Booking added Successfully");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
    	logger.info("Updating Booking");
        Assert.notNull(id, "Id cannot be null");
        Assert.notNull(booking, "Booking cannot be null");

        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found with id: " + id));

        existingBooking.setCustomerName(booking.getCustomerName());
        existingBooking.setCustomerContact(booking.getCustomerContact());
        existingBooking.setCustomerAddress(booking.getCustomerAddress());
        existingBooking.setRoomNumber(booking.getRoomNumber());
        existingBooking.setCheckInDate(booking.getCheckInDate());
        existingBooking.setCheckOutDate(booking.getCheckOutDate());
        existingBooking.setTotalPrice(booking.getTotalPrice()); // Update total price
        logger.info("Booking Updated Successfully");
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(String id) {
    	logger.info("Deleting Booking ");
        Assert.notNull(id, "Id cannot be null");
        try {
            bookingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        	logger.info("Booking Deleted Successfully");
            throw new NotFoundException("Booking not found with id: " + id);
        }
    }
}
