package com.example.hospitalservice.repositories;

import java.util.Date;
import java.util.Set;

import com.example.hospitalservice.entities.Patient;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Modifying
    @Query(value = "delete from patients p where p.id in ?1 and where created_at >= ?2 AND created_at <= ?3", nativeQuery = true)
    void deletePatientsById(Set<Integer> ids, Date startDate, Date endDate);

    @Query(value = "select * from patient p where p.age = ?1", nativeQuery = true)
    Set<Patient> fetchPatientsUptoSomeYearsOld(int age);
}