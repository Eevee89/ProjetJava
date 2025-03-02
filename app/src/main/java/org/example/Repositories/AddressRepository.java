package org.example.Repositories;

import java.util.List;

import org.example.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(nativeQuery = true, value="SELECT a.city FROM Address a INNER JOIN Center c ON a.id = c.address_id")
    public List<String> findAllCentersCities();
}
