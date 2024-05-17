package com.microservice.room.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.room.entity.HotelRoom;

public interface HotelRoomRepository extends MongoRepository<HotelRoom, String> {
	
//	List<Flight> findByTakeoffAndLanding(String takeoff, String landing);
	
	List<HotelRoom> findByRoomTypeAndRoomService(String roomType, String roomService);
}
