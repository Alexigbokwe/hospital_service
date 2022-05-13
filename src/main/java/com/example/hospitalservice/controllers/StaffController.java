package com.example.hospitalservice.controllers;

import javax.validation.Valid;

import com.example.hospitalservice.dto.StaffDto;
import com.example.hospitalservice.entities.Staff;
import com.example.hospitalservice.services.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping("/register")
    public ResponseEntity<Staff> save(@RequestBody @Valid StaffDto CreateStaffRequest) {
        Staff staff = this.staffService.createStaff(CreateStaffRequest);
        return ResponseEntity.ok(staff);
    }

    @PatchMapping("/update")
    public ResponseEntity<Staff> update(@RequestBody @Valid StaffDto staffUpdateRequest) {
        Staff staff = this.staffService.updateStaff(staffUpdateRequest);
        return ResponseEntity.ok(staff);
    }
}
