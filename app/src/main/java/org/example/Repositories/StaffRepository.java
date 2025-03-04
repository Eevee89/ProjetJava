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
    @Query("SELECT s FROM Staff s JOIN FETCH s.workTimes w WHERE w.id = :worktime")
    public List<Staff> findByFreeAt(@Param("worktime") int worktime);

    @Query("SELECT s FROM Staff s "+
           "JOIN FETCH s.workTimes w "+
           "JOIN FETCH s.centers c "+
           "WHERE s.privilege = 0 AND w.id = :worktime AND c.id = :center")
    public List<Staff> findWorkingByCenter(@Param("worktime") int worktime, @Param("center") int center);

    // Trouver tous les administrateurs
    @Query("SELECT s FROM Staff s JOIN s.centers c WHERE s.privilege = :privilege AND c.id = :centerId")
    List<Staff> findByPrivilege(int privilege);

    // Trouve le staff à partir d'un mail
    Staff findByEmail(String email);
    
    @Query("SELECT count(s) FROM Staff s WHERE s.email = :email")
    int countByEmail(@Param("email") String email);

    @Query("SELECT s.password FROM Staff s WHERE s.email = :email")
    String findPasswordWithEmail(@Param("email") String email);

    @Query("SELECT s FROM Staff s WHERE s.email = :email")
    Staff findStaffByEmail(@Param("email") String email);

    List<Staff> findByCenters_Id(int centerId);

    @Query("SELECT s FROM Staff s WHERE s.email LIKE CONCAT('%', :email, '%') AND s.privilege = 2")
    List<Staff> findDoctorsByEmailContaining(@Param("email") String email);

    @Query("SELECT DISTINCT s FROM Staff s LEFT JOIN s.centers c WHERE s.privilege = 1 AND s.email LIKE CONCAT('%', :email, '%')")
    List<Staff> findAdminsByEmailContaining(@Param("email") String email);
}
