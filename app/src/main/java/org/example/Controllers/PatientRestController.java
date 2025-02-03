package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Services.PatientService;
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
public class PatientRestController {

    @Autowired
    private PatientService service;

    @GetMapping(path = "api/patients")
    public List<Patient> findAll(@RequestParam(name = "firstName", required = false)String filterByName,
                                @RequestParam(name = "city", required = false)String city){

        String[] names = (filterByName == null ? "" : filterByName).split(",");
        String[] cities = (city == null ? "" : city).split(",");

        if (cities[0] == "") {
            if (names[0] == "") {
                return service.fetchAll();
            }
            return service.findByName(names);
        }
        if (names[0] == "") {
            return service.findByCity(cities);   
        }
        return service.findAll(names, cities);
    }
    
    @GetMapping(path = "api/patient/{id}")
    public Patient findById(@PathVariable("id") Integer id) throws PatientNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "api/patients")
    public ResponseEntity<Patient> create(@RequestBody Patient p) throws URISyntaxException{
        service.create(p);
        return ResponseEntity.created(new URI("patient/"+p.getId())).build();
    }


    @ExceptionHandler
    public ResponseEntity<String> handle(PatientNotFoundException ex){
        return ResponseEntity.badRequest().body("Le patient n'existe pas\n");
    }
}