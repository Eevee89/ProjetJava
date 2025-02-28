package org.example.Repositories;

import org.example.Entities.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer> {

    @Query("SELECT wt.id FROM WorkTime wt WHERE wt.day = :day AND wt.startHour = :start AND wt.endHour = :end")
    public Integer findByDayAndHour(@Param("day") String day, @Param("start") int start, @Param("end") int end);
}
