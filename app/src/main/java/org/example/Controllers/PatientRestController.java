package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.example.Entities.Patient;
import org.example.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientRestController {

    @Autowired
    private PatientService service;

    @PostMapping(path = "api/patients/create")
    public ResponseEntity<Patient> create(@RequestBody Patient p) throws URISyntaxException{
        service.create(p);
        return ResponseEntity.created(new URI("api/patient/"+p.getId())).build();
    }
}