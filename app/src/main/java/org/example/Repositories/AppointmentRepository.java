package org.example.Repositories;

import java.util.List;

import org.example.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
    @Query("SELECT a FROM Appointment a WHERE a.centerId.id = :center AND a.doctorId.id = :doctor AND a.patientId.id = :patient")
    public List<Appointment> findByCenterDoctorAndPatient(@Param("center") int center, @Param("doctor") int doctor, @Param("patient") int patient);
}
