package com.example.hospitalservice.repositories;

import java.util.Date;
import java.util.Set;

import com.example.hospitalservice.entities.Patient;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Modifying
    @Query(value = "DELETE FROM patients p WHERE p.created_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    void deletePatientsById(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select * from patients p where p.age = ?1", nativeQuery = true)
    Set<Patient> fetchPatientsUptoSomeYearsOld(int age);
}