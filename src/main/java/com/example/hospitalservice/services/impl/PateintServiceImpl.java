package com.example.hospitalservice.services.impl;

import java.util.Set;

import javax.transaction.Transactional;

import com.example.hospitalservice.dto.DeletePatientsDto;
import com.example.hospitalservice.entities.Patient;
import com.example.hospitalservice.exception.UserNotFoundException;
import com.example.hospitalservice.repositories.PatientRepository;
import com.example.hospitalservice.services.PatientService;
import com.example.hospitalservice.utils.CsvHelper;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class PateintServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public ByteArrayInputStream fetchPatientByIdToCsv(int patient_id) {
        Optional<Patient> patient = this.patientRepository.findById(Long.valueOf(patient_id));
        if (patient.isEmpty()) {
            throw new UserNotFoundException("patient not found");
        }
        ByteArrayInputStream in = CsvHelper.dataToCSV(patient.get());
        return in;
    }

    @Override
    public Set<Patient> fetchAllPatients(int yearConstrient) {
        return this.patientRepository.fetchPatientsUptoSomeYearsOld(yearConstrient);
    }

    @Transactional
    @Override
    public void deletePatients(DeletePatientsDto data) {
        this.patientRepository.deletePatientsById(data.getIds(), data.getStart_date(), data.getEnd_date());
    }
}
