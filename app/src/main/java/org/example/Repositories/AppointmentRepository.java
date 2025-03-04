package org.example.Repositories;

import java.util.List;
import java.util.Date;
import java.time.LocalDateTime;

import org.example.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.center.id = :center AND a.time = :date")
    public List<Appointment> findByCenterDoctorAndDate(@Param("center") int center, @Param("date") Date date);

    @Query("SELECT a FROM Appointment a WHERE a.center.id = :centerId AND a.time = :time")
    List<Appointment> findByCenterAndTime(@Param("centerId") int centerId, @Param("time") LocalDateTime time);
}
