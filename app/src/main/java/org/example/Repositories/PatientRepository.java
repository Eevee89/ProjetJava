package org.example.Repositories;


import java.util.List;
import org.example.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p WHERE p.firstName IN (:names) AND p.address.city IN (:cities)")
    List<Patient> findWithFirstNameOrCity(@Param("names") String[] names, @Param("cities") String[] cities);

    @Query("SELECT p FROM Patient p WHERE p.firstName IN (:names)")
    List<Patient> findByName(@Param("names") String[] names);

    @Query("SELECT p FROM Patient p WHERE p.email LIKE :email")
    Patient findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Patient p WHERE p.address.city IN (:cities)")
    List<Patient> findByCity(@Param("cities") String[] cities);

    // Récupérer le mot de passe d'un patient à partir de son email
    @Query("SELECT p.password FROM Patient p WHERE p.email = :email")
    String findPasswordWithEmail(String email);

    
    @Query("UPDATE Patient p SET p.vaccines = :vaccines WHERE p.id = :id")
    void updateVaccinesById(@Param("id") int id, @Param("vaccines") List<String> vaccines);

} 