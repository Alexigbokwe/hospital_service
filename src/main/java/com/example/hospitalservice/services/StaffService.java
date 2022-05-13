package com.example.hospitalservice.services;

import com.example.hospitalservice.dto.StaffDto;
import com.example.hospitalservice.entities.Staff;

public interface StaffService {
    Staff createStaff(StaffDto staffDetails);

    Staff getByStaffId(int staff_id);

    Staff updateStaff(StaffDto staffDetails);

    Boolean validateStaffUUID(String uuid);
}
