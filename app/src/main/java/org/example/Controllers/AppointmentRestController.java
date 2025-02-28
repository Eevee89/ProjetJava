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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentRestController {
    
    @Autowired
    private AppointmentService service;

    @Autowired
    private AuthService authService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PatientService patientService;

    @PostMapping("api/appointments/create")
    public ResponseEntity<Appointment> create(@RequestHeader("Custom-Auth") String userDatas, @RequestBody Appointment a) throws URISyntaxException, UnauthentifiedException
    {
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        System.out.println(a.gettime());

        int doctorId = service.isBusy(a.getCenterId().getId(), a.gettime());
        if (doctorId == -1) {
            return ResponseEntity.badRequest().build();
        }
        else {
            int patientId = authService.findIdByEmail(userDatas);
            try {
                Staff doctor = staffService.findOne(doctorId);
                a.setDoctorId(doctor);
                Patient patient = patientService.findOne(patientId);
                a.setPatientId(patient);
                service.create(a);
                return ResponseEntity.created(new URI("appointment/"+a.getId())).build();
            } catch (StaffNotFoundException s) {
                return ResponseEntity.internalServerError().build();
            } catch (PatientNotFoundException p) {
                return ResponseEntity.internalServerError().build();
            }
        }
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
