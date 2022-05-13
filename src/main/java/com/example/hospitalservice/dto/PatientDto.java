package com.example.hospitalservice.dto;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.example.hospitalservice.entities.PatientVisitHistory;
import lombok.Data;

@Data
public class PatientDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "patient name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 1, message = "patient should be at least 1 year old")
    private Integer age;

    private Set<PatientVisitHistory> patientVisitHistory;
}
