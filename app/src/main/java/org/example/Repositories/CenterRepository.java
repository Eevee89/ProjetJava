package org.example.Repositories;

import java.util.List;

import org.example.Entities.Appointment;
import org.example.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {
}
