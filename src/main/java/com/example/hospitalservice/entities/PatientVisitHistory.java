package com.example.hospitalservice.entities;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patient_visit_history")
@Data
public class PatientVisitHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientDetails;

    @Column(nullable = false)
    private Date visit_date;
}
