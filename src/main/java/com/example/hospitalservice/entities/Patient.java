package com.example.hospitalservice.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "patientDetails", fetch = FetchType.EAGER)
    private Set<PatientVisitHistory> patientVisitHistory;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at = new Date();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updated_at = new Date();
}
