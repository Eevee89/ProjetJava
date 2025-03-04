package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.example.Entities.Appointment;
import org.example.Entities.Patient;
import org.example.Entities.Staff;
import org.example.Exceptions.AppointmentNotFoundException;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Exceptions.StaffNotFoundException;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Services.AppointmentService;
import org.example.Services.AuthService;
import org.example.Services.PatientService;
import org.example.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentRestController {
    
    @Autowired
    private AppointmentService service;

    @Autowired
    private AuthService authService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PatientService patientService;

    @PostMapping("")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment a, @RequestHeader("Custom-Auth") String userDatas) 
            throws UnauthentifiedException {
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        System.out.println(a.getTime());

        int doctorId = service.isBusy(a.getCenter().getId(), a.getTime());
        if (doctorId == -1) {
            return ResponseEntity.badRequest().build();
        }

        Staff doctor = staffService.findById(doctorId);
        Patient patient = patientService.findById(a.getPatient().getId());

        if (doctor != null && patient != null) {
            a.setDoctor(doctor);
            a.setPatient(patient);
            return ResponseEntity.ok(service.save(a));
        }

        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(AppointmentNotFoundException ex){
        return ResponseEntity.badRequest().body("Le rendez-vous n'existe pas\n");
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UnauthentifiedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
