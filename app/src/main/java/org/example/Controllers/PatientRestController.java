package org.example.Controllers;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Exceptions.UnauthorizedException;
import org.example.Entities.Patient;
import org.example.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.Entities.AuthHeader;
import org.example.Services.AuthService;

@RestController
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "api/patients/create")
    public ResponseEntity<Patient> create(@RequestBody Patient p) throws URISyntaxException{
        patientService.create(p);
        return ResponseEntity.created(new URI("api/patient/"+p.getId())).build();
    }

    // Récupérer tous les patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
            
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        boolean isStaff = authService.isStaff(userDatas);
        if (!isStaff) {
            throw new UnauthorizedException("L'utilisateur doit être Staff utiliser cette fonctionnalité");
        }
        
        List<Patient> patients = patientService.fetchAll();
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }

     @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(
        @RequestParam String[] name,
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
            
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        boolean isStaff = authService.isStaff(userDatas);
        if (!isStaff) {
            throw new UnauthorizedException("L'utilisateur doit être Staff utiliser cette fonctionnalité");
        }
        
        List<Patient> patients = patientService.findByName(name);
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }
    
    @ExceptionHandler
    public ResponseEntity<String> handle(UnauthentifiedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}