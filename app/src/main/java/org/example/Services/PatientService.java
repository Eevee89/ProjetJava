package org.example.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private static List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll(String name){
        return patients.stream()
            .filter(p->p.getFirstName().startsWith(name))
            .toList();
    }

    public Patient findOne(Integer id) throws PatientNotFoundException{
        return repository.findById(id)
            .orElseThrow(PatientNotFoundException::new);
    }

    public void create(Patient p){
        patients.add(p);
    }

    public void removeOne(Integer id){
        patients.removeIf(p->p.getId().equals(id));
    }
}