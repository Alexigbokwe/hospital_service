package com.example.hospitalservice.repositories;

import java.util.Optional;

import com.example.hospitalservice.entities.Staff;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    @Query(value = "select * from staffs s where s.uuid = ?1", nativeQuery = true)
    Optional<Staff> findByStaffUUID(String uuid);
}
