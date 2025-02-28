package org.example.Services;

import java.util.List;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository repository;

    public List<Patient> findAll(String[] names, String[] cities){
        return repository.findWithFirstNameOrCity(names, cities);
    }

    public List<Patient> fetchAll() {
        return repository.findAll();
    }

    public List<Patient> findByName(String[] names) {
        return repository.findByName(names);
    }

    public List<Patient> findByCity(String[] cities) {
        return repository.findByCity(cities);
    }

    public Patient findOne(Integer id) throws PatientNotFoundException{
        return repository.findById(id)
            .orElseThrow(PatientNotFoundException::new);
    }

    public void create(Patient p){
        repository.save(p);
    }
}