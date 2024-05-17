package com.microservice.booking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    @NotEmpty(message = "Customer name is required")
    @NotNull(message = "Customer name cannot be null")
    private String customerName;

    @NotEmpty(message = "Customer contact is required")
    @NotNull(message = "Customer contact cannot be null")
    private String customerContact;

    @NotEmpty(message = "Customer address is required")
    @NotNull(message = "Customer address cannot be null")
    private String customerAddress;

    @NotEmpty(message = "Room Number is required")
    @NotNull(message = "Room  Number cannot be null")
    private String roomNumber;

    @NotNull(message = "Check-in date is required")
    private Date checkInDate;

    @NotNull(message = "Check-out date is required")
    private Date checkOutDate;

    private double totalPrice;

	public Booking(String id,
			@NotEmpty(message = "Customer name is required") @NotNull(message = "Customer name cannot be null") String customerName,
			@NotEmpty(message = "Customer contact is required") @NotNull(message = "Customer contact cannot be null") String customerContact,
			@NotEmpty(message = "Customer address is required") @NotNull(message = "Customer address cannot be null") String customerAddress,
			@NotEmpty(message = "Room Number is required") @NotNull(message = "Room  Number cannot be null") String roomNumber,
			@NotNull(message = "Check-in date is required") Date checkInDate,
			@NotNull(message = "Check-out date is required") Date checkOutDate, double totalPrice) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerAddress = customerAddress;
		this.roomNumber = roomNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
