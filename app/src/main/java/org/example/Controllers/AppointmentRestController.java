package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.example.Entities.Appointment;
import org.example.Exceptions.AppointmentNotFoundException;
import org.example.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

@RestController
public class AppointmentRestController {
    
    @Autowired
    private AppointmentService service;

    @GetMapping("/appointment/{id}")
    public Appointment findOne(@PathVariable("id") Integer id) throws AppointmentNotFoundException {
        return service.findOne(id);
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> create(@RequestBody Appointment a) throws URISyntaxException{
        if (service.isBusy(a.getCenterId().getId(), a.getDoctorId().getId(), a.getPatientId().getId(), a.gettime())) {
            return ResponseEntity.badRequest().build();
        }
        else {
            service.create(a);
            return ResponseEntity.created(new URI("appointment/"+a.getId())).build();
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(AppointmentNotFoundException ex){
        return ResponseEntity.badRequest().body("Le rendez-vous n'existe pas\n");
    }
}
