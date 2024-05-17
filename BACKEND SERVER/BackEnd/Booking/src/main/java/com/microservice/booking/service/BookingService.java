package com.microservice.booking.service;


import java.util.List;

import com.microservice.booking.entity.Booking;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(String id);

    Booking addBooking(Booking booking);

    Booking updateBooking(String id, Booking booking);

    void deleteBooking(String id);
}
