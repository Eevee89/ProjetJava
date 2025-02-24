package org.example.Services;

import java.util.Optional;
import org.example.Entities.Center;
import org.example.Repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    public Center saveCenter(Center center) {
        return centerRepository.save(center);
    }

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
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
    
}
