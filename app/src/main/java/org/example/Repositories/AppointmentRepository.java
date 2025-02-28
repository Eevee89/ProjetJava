package org.example.Repositories;

import java.util.List;
import java.util.Date;

import org.example.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.centerId.id = :center AND a.time = :date")
    public List<Appointment> findByCenterDoctorAndDate(@Param("center") int center, @Param("date") Date date);
}
