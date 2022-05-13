package com.example.hospitalservice.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StaffDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "staff name should have at least 2 characters")
    private String name;

    private String uuid;

    private Date registration_date;
}
