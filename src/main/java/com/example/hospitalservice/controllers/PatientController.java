package com.example.hospitalservice.controllers;

import java.util.Set;

import com.example.hospitalservice.dto.DeletePatientsDto;
import com.example.hospitalservice.entities.Patient;
import com.example.hospitalservice.exception.InvalidStaffUUIDException;
import com.example.hospitalservice.services.PatientService;
import com.example.hospitalservice.services.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    protected StaffService staffService;

    @Autowired
    protected PatientService patientService;

    @GetMapping("/{staff_uuid}")
    public ResponseEntity<Set<Patient>> fetchAllPatients(@RequestParam String staff_uuid) {
        if (!this.staffService.validateStaffUUID(staff_uuid)) {
            throw new InvalidStaffUUIDException("Invalid staff uuid");
        }

        Integer numberOfYears = 2;
        Set<Patient> patient = this.patientService.fetchAllPatients(numberOfYears);

        return ResponseEntity.ok(patient);
    }

    @PatchMapping("/{staff_uuid}")
    public ResponseEntity<String> deletePatient(@RequestParam String staff_uuid,
            @RequestBody DeletePatientsDto patients) {
        if (!this.staffService.validateStaffUUID(staff_uuid)) {
            throw new InvalidStaffUUIDException("Invalid staff uuid");
        }

        this.patientService.deletePatients(patients);
        return ResponseEntity.status(HttpStatus.OK).body("patient(s) successfully removed");
    }

    @GetMapping("/csv/download/{staff_uuid}/{patient_id}")
    public ResponseEntity<Resource> getFile(@RequestParam String staff_uuid,
            @RequestParam int patient_id) {
        if (!this.staffService.validateStaffUUID(staff_uuid)) {
            throw new InvalidStaffUUIDException("Invalid staff uuid");
        }

        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(this.patientService.fetchPatientByIdToCsv(patient_id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
