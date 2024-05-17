package com.microservice.room.controller;
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
import com.microservice.room.entity.HotelRoom;
import com.microservice.room.service.HotelRoomService;
import com.microservie.room.exceptions.NotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rooms")
public class HotelRoomController {
	
	/*
	  author:Bijay Kuamr 
	 */

	Logger logger = LoggerFactory.getLogger(HotelRoomController.class);
    @Autowired
    private HotelRoomService hotelRoomService;

    @GetMapping
    public List<HotelRoom> getAllRooms() {
        return hotelRoomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelRoom> getRoomById(@PathVariable String id) {
        try {
        	logger.info("Getting list of room by ID");
            HotelRoom room = hotelRoomService.getRoomById(id);
            return ResponseEntity.ok(room);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{roomType}/{roomService}")
    public ResponseEntity<?> getRoomByRoomType(@PathVariable String roomType, @PathVariable String roomService) {
    	logger.info("Getting list of room by roomtype");
    	ResponseEntity<?> responseEntity = null;
    	List<HotelRoom> rooms = hotelRoomService.getRoomByRoomTypeAndRoomService(roomType,roomService);
    	responseEntity = new ResponseEntity<>(rooms, HttpStatus.OK);
    	return responseEntity;
    	
    }
    
//    @GetMapping("/flight/{takeoff}/{landing}")
//	public ResponseEntity<?> getFlightByTakeoff(@PathVariable String takeoff, @PathVariable String landing) {
//		logger.info("Getting flight by takeoff or landing");
//		ResponseEntity<?> responseEntity = null;
//		List<Flight> flights = flightService.getFlightBySourceAndDestination(takeoff, landing);
//		responseEntity = new ResponseEntity<>(flights, HttpStatus.OK);
//		logger.info("Successfull search of flight by takeoff or landing");
//		return responseEntity;
//	}

    @PostMapping()
    public ResponseEntity<HotelRoom> addRoom(@Valid @RequestBody HotelRoom room) {
    	logger.info("Adding Room");
        HotelRoom createdRoom = hotelRoomService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelRoom> updateRoom(@PathVariable String id, @Valid @RequestBody HotelRoom room) {
        try {
        	logger.info("Updating Room");
            HotelRoom updatedRoom = hotelRoomService.updateRoom(id, room);
            return ResponseEntity.ok(updatedRoom);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        try {
        	logger.info("Deleting Room");
            hotelRoomService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
