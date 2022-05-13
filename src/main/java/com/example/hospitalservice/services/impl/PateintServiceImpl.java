package com.example.hospitalservice.services.impl;

import java.util.Set;

import javax.transaction.Transactional;

import com.example.hospitalservice.dto.DeletePatientsDto;
import com.example.hospitalservice.entities.Patient;
import com.example.hospitalservice.exception.InvalidInputException;
import com.example.hospitalservice.exception.UserNotFoundException;
import com.example.hospitalservice.repositories.PatientRepository;
import com.example.hospitalservice.services.PatientService;
import com.example.hospitalservice.utils.CsvHelper;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
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
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getStart_date() + " 00:00:00");
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getEnd_date() + " 00:00:00");
            this.patientRepository.deletePatientsById(startDate, endDate);
        } catch (ParseException e) {
            System.out.println(e);
            throw new InvalidInputException("Invalid date format. Valid format is yyyy-MM-dd HH:mm:ss");
        }
    }
}
