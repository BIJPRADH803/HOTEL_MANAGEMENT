package com.microservice.room.service;
import java.util.List;

import com.microservice.room.entity.HotelRoom;

public interface HotelRoomService {

    List<HotelRoom> getAllRooms();

    HotelRoom getRoomById(String id);
    
    List<HotelRoom> getRoomByRoomTypeAndRoomService(String roomType, String roomService);
    
//    List<Flight> getFlightBySourceAndDestination(String takeoff, String landing);

    HotelRoom addRoom(HotelRoom room);

    HotelRoom updateRoom(String id, HotelRoom room);

    void deleteRoom(String id);
}
