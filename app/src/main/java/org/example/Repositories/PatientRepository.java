package org.example.Repositories;

import java.util.List;
import org.example.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE :firstName%")
    List<Patient> findWithFirstNameBeginningWith(@Param("firstName") String firstName);
} 