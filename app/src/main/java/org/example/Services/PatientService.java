package org.example.Services;

import java.util.List;
import java.util.ArrayList;
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

    public void addVaccine(int patientId, String vaccine) {
        Patient patient = repository.findById(patientId).orElse(null);
        if (patient == null) {
            throw new RuntimeException("Patient non trouvé");
        }

        List<String> vaccines = patient.getVaccines();
        if (vaccines == null) {
            vaccines = new ArrayList<>();
        }
        vaccines.add(vaccine);

        repository.updateVaccinesById(patientId, vaccines);
    }
}