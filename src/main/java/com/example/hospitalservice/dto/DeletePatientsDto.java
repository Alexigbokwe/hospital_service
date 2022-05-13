package com.example.hospitalservice.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DeletePatientsDto {
    @NotEmpty
    String start_date;
    @NotEmpty
    String end_date;
}
