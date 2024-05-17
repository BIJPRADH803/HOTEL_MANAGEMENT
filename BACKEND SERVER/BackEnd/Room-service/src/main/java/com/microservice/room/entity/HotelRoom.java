package com.microservice.room.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "hotelRooms")
public class HotelRoom {

    @Id
    private String id;

    @NotEmpty(message = "Room number is required")
    @NotNull(message = "Room number cannot be null")
    private String roomNumber;

    @NotEmpty(message = "Room type is required")
    @NotNull(message = "Room type cannot be null")
    private String roomType;
    
    @NotEmpty(message = "Room Service is required")
    @NotNull(message = "Room Service cannot be null")
    private String roomService;

    @NotNull(message = "Price is required")
    private Double price;

	public HotelRoom(String id,
			@NotEmpty(message = "Room number is required") @NotNull(message = "Room number cannot be null") String roomNumber,
			@NotEmpty(message = "Room type is required") @NotNull(message = "Room type cannot be null") String roomType,
			@NotEmpty(message = "Room Service is required") @NotNull(message = "Room Service cannot be null") String roomService,
			@NotNull(message = "Price is required") Double price) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomService = roomService;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomService() {
		return roomService;
	}

	public void setRoomService(String roomService) {
		this.roomService = roomService;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
    
    

	
}
