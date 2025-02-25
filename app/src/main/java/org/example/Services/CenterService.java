package org.example.Services;

import java.util.List;

import org.example.Entities.Center;
import org.example.Exceptions.CenterNotFoundException;
import org.example.Repositories.CenterRepository;
import org.example.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {
    
    @Autowired
    private CenterRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public Center findOne(int id) throws CenterNotFoundException {
        return repository.findById(id).orElseThrow(CenterNotFoundException::new);
    }

    public List<Center> findAll() {
        return repository.findAll();
    }

    public List<String> getAllCities() {
        return addressRepository.findAllCentersCities();
    }
}
