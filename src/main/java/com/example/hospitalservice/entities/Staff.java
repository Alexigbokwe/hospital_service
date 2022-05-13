package com.example.hospitalservice.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Table(name = "staffs")
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String uuid;

    @Column(nullable = false)
    private Date registration_date = new Date();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at = new Date();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updated_at = new Date();

    @PrePersist
    public void autofill() {
        this.setUuid(UUID.randomUUID().toString());
    }
}