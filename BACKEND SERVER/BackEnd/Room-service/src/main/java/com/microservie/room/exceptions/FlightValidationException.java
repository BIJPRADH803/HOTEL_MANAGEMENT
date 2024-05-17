package com.microservie.room.exceptions;

public class FlightValidationException extends RuntimeException{
	public static final long serialVersionUID = 1L;



	public String message;


	public FlightValidationException() {
		super();
		message = "Id already Existing";
	}




	public String getMessage(){

		return message;
	}
}
	