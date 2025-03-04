package org.example.Repositories;

import org.example.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {
    List<Center> findByNameContainingIgnoreCaseOrAddress_CityContainingIgnoreCase(String name, String city);
}
