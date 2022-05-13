package com.example.hospitalservice.services;

import java.util.Set;

import com.example.hospitalservice.dto.DeletePatientsDto;
import com.example.hospitalservice.entities.Patient;

public interface PatientService {
    Set<Patient> fetchAllPatients(int yearConstrient);

    void deletePatients(DeletePatientsDto data);
}
