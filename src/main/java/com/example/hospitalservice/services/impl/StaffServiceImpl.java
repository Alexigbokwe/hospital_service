package com.example.hospitalservice.services.impl;

import java.util.Date;
import java.util.Optional;

import com.example.hospitalservice.dto.StaffDto;
import com.example.hospitalservice.entities.Staff;
import com.example.hospitalservice.exception.InvalidStaffUUIDException;
import com.example.hospitalservice.exception.UserNotFoundException;
import com.example.hospitalservice.repositories.StaffRepository;
import com.example.hospitalservice.services.StaffService;
import com.example.hospitalservice.utils.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    protected StaffRepository staffRepository;

    @Autowired
    protected ModelMapper modelMapper;

    @Override
    public Staff createStaff(StaffDto staffDetails) {
        staffDetails.setRegistration_date(new Date());
        Staff newStaff = this.modelMapper.map(staffDetails, Staff.class);

        Staff savedStaff = this.staffRepository.save(newStaff);

        return savedStaff;
    }

    @Override
    public Staff getByStaffId(int staff_id) {
        Optional<Staff> staff = this.staffRepository.findById(Long.valueOf(staff_id));
        if (staff.isEmpty()) {
            throw new UserNotFoundException("Staff not found");
        }
        return staff.get();

    }

    @Override
    public Staff updateStaff(StaffDto staffDetail) {
        if (!this.validateStaffUUID(staffDetail.getUuid())) {
            throw new InvalidStaffUUIDException("Invalid staff uuid");
        }

        Optional<Staff> staffRecord = this.staffRepository.findByStaffUUID(staffDetail.getUuid());
        if (staffRecord.isEmpty()) {
            throw new UserNotFoundException("Staff not found");
        }

        Staff staffDBRecord = staffRecord.get();
        staffDBRecord.setName(staffDetail.getName());

        Staff savedStaff = this.staffRepository.save(staffDBRecord);

        return savedStaff;
    }

    public Boolean validateStaffUUID(String uuid) {
        if (UUID.isValidUUID(uuid)) {
            Optional<Staff> staffRecord = this.staffRepository.findByStaffUUID(uuid);
            if (staffRecord.isEmpty()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
