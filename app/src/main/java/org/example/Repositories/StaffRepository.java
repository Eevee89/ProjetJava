package org.example.Repositories;

import java.util.List;

import org.example.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    
    @Query("SELECT s FROM Staff s JOIN FETCH s.centers c WHERE c.id IN (:centers)")
    public List<Staff> findByCenter(@Param("centers") int[] centers);

    @Query("SELECT s FROM Staff s JOIN FETCH s.workTimes w WHERE w.id = :worktime")
    public List<Staff> findByFreeAt(@Param("worktime") int worktime);
}
