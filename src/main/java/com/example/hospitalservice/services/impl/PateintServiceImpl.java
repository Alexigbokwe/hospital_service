package com.example.hospitalservice.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.hospitalservice.dto.DeletePatientsDto;
import com.example.hospitalservice.entities.Patient;
import com.example.hospitalservice.repositories.PatientRepository;
import com.example.hospitalservice.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;

public class PateintServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

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
