package com.example.hospitalservice.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class DeletePatientsDto {
    Set<Integer> ids;
    Date start_date;
    Date end_date;
}
