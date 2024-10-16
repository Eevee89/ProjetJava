package org.example.Services;

import java.util.Date;
import java.util.List;

import org.example.Entities.Appointment;
import org.example.Exceptions.AppointmentNotFoundException;
import org.example.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public boolean isBusy(int center, int doctor, int patient, Date dateToCheck) {
        List<Appointment> res = repository.findByCenterDoctorAndPatient(center, doctor, patient);
        return res.removeIf(app -> app.gettime().compareTo(dateToCheck)==0); // To know if the given hour is busy or not 
    }

    public Appointment findOne(Integer id) throws AppointmentNotFoundException{
        return repository.findById(id)
            .orElseThrow(AppointmentNotFoundException::new);
    }

    public void create(Appointment a) {
        repository.save(a);
    }
}
