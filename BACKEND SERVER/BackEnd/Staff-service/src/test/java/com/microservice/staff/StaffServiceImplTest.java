package com.microservice.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
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
import com.microservice.staff.entity.Staff;
import com.microservice.staff.repository.StaffRepository;
import com.microservice.staff.service.StaffServiceImpl;
import com.microservie.staff.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class StaffServiceImplTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffServiceImpl staffService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("1", "John Doe", "Manager", 50000));
        when(staffRepository.findAll()).thenReturn(staffList);

        List<Staff> result = staffService.getAllStaff();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Manager", result.get(0).getPosition());
        assertEquals(50000, result.get(0).getSalary());
    }

    @Test
    void testGetStaffById_Success() {
        Staff staff = new Staff("1", "John Doe", "Manager", 50000);
        when(staffRepository.findById("1")).thenReturn(Optional.of(staff));

        Staff result = staffService.getStaffById("1");

        assertEquals("John Doe", result.getName());
        assertEquals("Manager", result.getPosition());
        assertEquals(50000, result.getSalary());
    }

    @Test
    void testGetStaffById_NotFound() {
        when(staffRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> staffService.getStaffById("1"));
    }

    @Test
    void testAddStaff_Success() {
        Staff staff = new Staff(null, "John Doe", "Manager", 50000);
        when(staffRepository.save(staff)).thenReturn(new Staff("1", "John Doe", "Manager", 50000));

        Staff result = staffService.addStaff(staff);

        assertEquals("John Doe", result.getName());
        assertEquals("Manager", result.getPosition());
        assertEquals(50000, result.getSalary());
    }

    @Test
    void testUpdateStaff_Success() {
        Staff existingStaff = new Staff("1", "John Doe", "Manager", 50000);
        Staff updatedStaff = new Staff("1", "Jane Doe", "Director", 60000);
        when(staffRepository.findById("1")).thenReturn(Optional.of(existingStaff));
        when(staffRepository.save(existingStaff)).thenReturn(updatedStaff);

        Staff result = staffService.updateStaff("1", updatedStaff);

        assertEquals("Jane Doe", result.getName());
        assertEquals("Director", result.getPosition());
        assertEquals(60000, result.getSalary());
    }

    @Test
    void testUpdateStaff_NotFound() {
        when(staffRepository.findById("1")).thenReturn(Optional.empty());
        Staff updatedStaff = new Staff("1", "Jane Doe", "Director", 60000);

        assertThrows(NotFoundException.class, () -> staffService.updateStaff("1", updatedStaff));
    }

    @Test
    void testDeleteStaff_Success() {
        doNothing().when(staffRepository).deleteById("1");

        staffService.deleteStaff("1");
    }
}
