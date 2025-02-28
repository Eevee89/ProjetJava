package org.example.Repositories;

import java.util.List;

import org.example.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    
    // Trouver les membres du staff appartenant à certains centres
    @Query("SELECT s FROM Staff s JOIN s.centers c WHERE c.id IN (:centers)")
    List<Staff> findByCenter(@Param("centers") int[] centers);

    // Trouver les membres du staff disponibles à un créneau spécifique
    @Query("SELECT s FROM Staff s JOIN s.workTimes w WHERE w.id = :worktime")
    List<Staff> findByFreeAt(@Param("worktime") int worktime);

    // Vérifier si un staff existe avec un ID donné
    boolean existsById(int id);

    // Trouver tous les médecins d'un centre
    List<Staff> findByPrivilegeAndCenters_Id(int privilege, int centerId);

    // Trouver tous les administrateurs
    List<Staff> findByPrivilege(int privilege);
}
