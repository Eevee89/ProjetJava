package org.example.Services;

import java.util.List;
import java.util.Optional;

import org.example.Entities.Center;
import org.example.Repositories.CenterRepository;
import org.example.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Center saveCenter(Center center) {
        return centerRepository.save(center);
    }

    public Center updateCenter(int id, Center updatedCenter) {
        return centerRepository.findById(id)
                .map(center -> {
                    center.setPhone(updatedCenter.getPhone());
                    center.setAddress(updatedCenter.getAddress());
                    return centerRepository.save(center);
                })
                .orElse(null);
    }
    // Supprimer un centre par ID
    public void deleteCenter(int id) {
    centerRepository.deleteById(id);
    }

// (Optionnel) Récupérer un centre par ID
    public Optional<Center> getCenterById(int id) {
    return centerRepository.findById(id);
    }
  
    public List<String> getAllCities() {
        return addressRepository.findAllCentersCities();
    }

    public List<Center> findAll() {
        return centerRepository.findAll();
    }

    public List<Center> searchCenters(String query) {
        return centerRepository.findByNameContainingIgnoreCaseOrAddress_CityContainingIgnoreCase(query, query);
    }

}
