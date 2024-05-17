package com.microservice.booking.exceptions;

public class BookingValidationException extends RuntimeException{
	public static final long serialVersionUID = 1L;



	public String message;


	public BookingValidationException() {
		super();
		message = "Id already Existing";
	}




	public String getMessage(){

		return message;
	}
}
	