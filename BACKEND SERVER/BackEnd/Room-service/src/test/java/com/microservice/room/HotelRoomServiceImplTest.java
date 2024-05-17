package com.microservice.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.microservice.room.entity.HotelRoom;
import com.microservice.room.repository.HotelRoomRepository;
import com.microservice.room.service.HotelRoomServiceImpl;
import com.microservie.room.exceptions.NotFoundException;
//import com.microservie.room.exceptions.RoomNumberExistsException;

@ExtendWith(MockitoExtension.class)
class HotelRoomServiceImplTest {

    @Mock
    private HotelRoomRepository hotelRoomRepository;

    @InjectMocks
    private HotelRoomServiceImpl hotelRoomService;

    private HotelRoom room;

    @BeforeEach
    void setUp() {
        room = new HotelRoom("1", "101", "Single", "AC", 100.0);
    }

    @Test
    void testGetAllRooms() {
        List<HotelRoom> rooms = new ArrayList<>();
        rooms.add(room);
        when(hotelRoomRepository.findAll()).thenReturn(rooms);

        List<HotelRoom> result = hotelRoomService.getAllRooms();

        assertEquals(1, result.size());
        assertEquals(room, result.get(0));
    }

    @Test
    void testGetRoomById_Success() {
        when(hotelRoomRepository.findById("1")).thenReturn(Optional.of(room));

        HotelRoom result = hotelRoomService.getRoomById("1");

        assertEquals(room, result);
    }

    @Test
    void testGetRoomById_NotFound() {
        when(hotelRoomRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hotelRoomService.getRoomById("1"));
    }

    @Test
    void testGetRoomByRoomTypeAndRoomService() {
        List<HotelRoom> rooms = new ArrayList<>();
        rooms.add(room);
        when(hotelRoomRepository.findByRoomTypeAndRoomService("Single", "AC")).thenReturn(rooms);

        List<HotelRoom> result = hotelRoomService.getRoomByRoomTypeAndRoomService("Single", "AC");

        assertEquals(1, result.size());
        assertEquals(room, result.get(0));
    }

    @Test
    void testAddRoom_Success() {
//        when(hotelRoomRepository.findByRoomNumber("101")).thenReturn(Optional.empty());
        when(hotelRoomRepository.save(room)).thenReturn(room);

        HotelRoom result = hotelRoomService.addRoom(room);

        assertEquals(room, result);
    }
}
//    @Test
//    void testAddRoom_RoomNumberExists() {
////        when(hotelRoomRepository.findByRoomNumber("101")).thenReturn(Optional.of(room));
//
//        assertThrows(RoomNumberExistsException.class, () -> hotelRoomService.addRoom(room));
//    }

//    @Test
//    void testUpdateRoom_Success() {
//        when(hotelRoomRepository.existsById("1")).thenReturn(true);
//        when(hotelRoomRepository.save(room)).thenReturn(room);
//
//        HotelRoom result = hotelRoomService.updateRoom("1", room);
//
//        assertEquals(room, result);
//    }

//    @Test
//    void testUpdateRoom_NotFound() {
//        when(hotelRoomRepository.existsById("1")).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> hotelRoomService.updateRoom("1", room));
//    }

//    @Test
//    void testDeleteRoom_Success() {
//        when(hotelRoomRepository.existsById("1")).thenReturn(true);
//
//        hotelRoomService.deleteRoom("1");
//    }

//    @Test
//    void testDeleteRoom_NotFound() {
//        when(hotelRoomRepository.existsById("1")).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> hotelRoomService.deleteRoom("1"));
//    }
//}
