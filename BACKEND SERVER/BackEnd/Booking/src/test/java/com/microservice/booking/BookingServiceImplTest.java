package com.microservice.booking;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.booking.entity.Booking;
import com.microservice.booking.exceptions.NotFoundException;
import com.microservice.booking.repository.BookingRepository;
import com.microservice.booking.service.BookingServiceImpl;


@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking("1", "John Doe", "1234567890", "Address 1", "101", new Date(), new Date(), 100.0));
        when(bookingRepository.findAll()).thenReturn(bookingList);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getCustomerName());
        assertEquals("1234567890", result.get(0).getCustomerContact());
        assertEquals("Address 1", result.get(0).getCustomerAddress());
//        assertEquals("101", result.get(0).getRoomNumber());
        assertEquals(100.0, result.get(0).getTotalPrice());
    }

    @Test
    void testGetBookingById_Success() {
        Booking booking = new Booking("1", "John Doe", "1234567890", "Address 1", "101", new Date(), new Date(), 100.0);
        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));

        Booking result = bookingService.getBookingById("1");

        assertEquals("John Doe", result.getCustomerName());
        assertEquals("1234567890", result.getCustomerContact());
        assertEquals("Address 1", result.getCustomerAddress());
//        assertEquals("101", result.getRoomNumber());
        assertEquals(100.0, result.getTotalPrice());
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.getBookingById("1"));
    }

    @Test
    void testAddBooking() {
        Booking booking = new Booking("1", "John Doe", "1234567890", "Address 1", "101", new Date(), new Date(), 100.0);
        when(bookingRepository.save(booking)).thenReturn(booking);

        Booking result = bookingService.addBooking(booking);

        assertEquals("John Doe", result.getCustomerName());
        assertEquals("1234567890", result.getCustomerContact());
        assertEquals("Address 1", result.getCustomerAddress());
//        assertEquals("101", result.getRoomNumber());
        assertEquals(100.0, result.getTotalPrice());
    }

    @Test
    void testUpdateBooking_Success() {
        Booking existingBooking = new Booking("1", "John Doe", "1234567890", "Address 1", "101", new Date(), new Date(), 100.0);
        Booking updatedBooking = new Booking("1", "Jane Doe", "0987654321", "Address 2", "102", new Date(), new Date(), 200.0);
        when(bookingRepository.findById("1")).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(existingBooking)).thenReturn(updatedBooking);

        Booking result = bookingService.updateBooking("1", updatedBooking);

        assertEquals("Jane Doe", result.getCustomerName());
        assertEquals("0987654321", result.getCustomerContact());
        assertEquals("Address 2", result.getCustomerAddress());
//        assertEquals("102", result.getRoomNumber());
        assertEquals(200.0, result.getTotalPrice());
    }

    @Test
    void testUpdateBooking_NotFound() {
        when(bookingRepository.findById("1")).thenReturn(Optional.empty());
        Booking updatedBooking = new Booking("1", "Jane Doe", "0987654321", "Address 2", "102", new Date(), new Date(), 200.0);

        assertThrows(NotFoundException.class, () -> bookingService.updateBooking("1", updatedBooking));
    }

//    @Test
//    void testDeleteBooking_Success() {
//        // Stubbing the behavior of the repository
//        when(bookingRepository.existsById("1")).thenReturn(true);
//        doNothing().when(bookingRepository).deleteById("1");
//
//        // Execution
//        assertDoesNotThrow(() -> bookingService.deleteBooking("1"));
//
//        // Verification
//        verify(bookingRepository, times(1)).deleteById("1");
//    }

//
//    @Test
//    void testDeleteBooking_NotFound() {
//        when(bookingRepository.existsById("1")).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> bookingService.deleteBooking("1"));
//    }
}
