package com.microservice.staff.service;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.microservice.staff.entity.Staff;

public interface StaffService {
    List<Staff> getAllStaff();

    Staff getStaffById(String id);

    Staff addStaff(@Valid @NotNull Staff staff);

    Staff updateStaff(@NotNull String id, @Valid @NotNull Staff staff);

    void deleteStaff(String id);
}
