package com.microservice.room.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservice.room.entity.HotelRoom;
import com.microservice.room.repository.HotelRoomRepository;
import com.microservie.room.exceptions.NotFoundException;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    Logger logger = LoggerFactory.getLogger(HotelRoomServiceImpl.class);
    
    @Override
    public List<HotelRoom> getAllRooms() {
    	logger.info("Getting Room list");
    	logger.info(" Successfull search of all rooms");
        return hotelRoomRepository.findAll();
    }

    @Override
    public HotelRoom getRoomById(String id) {
    	logger.info("Getting Room by ID");
    	logger.info(" Successfull search of Room by ID");
        return hotelRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
    }
    
    public List<HotelRoom> getRoomByRoomTypeAndRoomService(@PathVariable String roomType, @PathVariable String roomService) {
    	logger.info("Getting Room by Roomtype");
    	
    	List<HotelRoom> list = hotelRoomRepository.findByRoomTypeAndRoomService(roomType,roomService);
    	if(list.isEmpty())
    		throw new NotFoundException("Room with " + roomType + " and Room Service " + roomService + " not exists");
    	logger.info(" Successfull search of room by roomtype");
    	return list;
    }
    
    
    
    
//    public List<Flight> getFlightBySourceAndDestination(@PathVariable String takeoff, @PathVariable String landing) {
//		logger.info("Getting flight by Source and Destination");
//		List<Flight> list = flightRepository.findByTakeoffAndLanding(takeoff, landing);
//		if (list.isEmpty())
//			throw new NotFoundException(
//					"Flight with takeoff " + takeoff + "and landing " + landing + " doesnot exist.");
//		logger.info("Successful search of flight by source and destination");
//		return list;
//
//	}

    @Override
    public HotelRoom addRoom(HotelRoom room) {
    	logger.info("Adding Room");
        Assert.notNull(room, "Room cannot be null");
        logger.info(" Successfully added room");
        return hotelRoomRepository.save(room);
    }

    @Override
    public HotelRoom updateRoom(String id, HotelRoom room) {
    	logger.info("Updating room ");
    	
        Assert.notNull(id, "Id cannot be null");
        Assert.notNull(room, "Room cannot be null");
        
        HotelRoom existingRoom = hotelRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
        existingRoom.setRoomNumber(room.getRoomNumber()); 
//        existingRoom.setType(room.getRoomType());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setPrice(room.getPrice());
        logger.info(" Successfully Updated room");
        return hotelRoomRepository.save(existingRoom);
    }
    
   
    @Override
    public void deleteRoom(String id) {
        Assert.notNull(id, "Id cannot be null");
        try {
        	logger.info("Deleting Room");
        	logger.info(" Successfully deleted room");
            hotelRoomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Room not found with id: " + id);
        }
    }
}
