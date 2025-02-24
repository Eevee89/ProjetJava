package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.example.Entities.Staff;
import org.example.Exceptions.StaffNotFoundException;
import org.example.Services.StaffService;
import org.example.Services.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

@RestController
public class StaffRestController {
    
    @Autowired
    private StaffService service;

    // Avoir la liste des médecins
    @GetMapping("/doctors")
    public ResponseEntity<?> findAll(
        @RequestParam(name = "centers", required = false) String center,
        @RequestParam(name = "day", required = false) String day,
        @RequestParam(name = "morning", required = false) String morning,
        @RequestParam(name = "staffPrivilege") int staffPrivilege) {

    // Vérifier l'autorisation : Seuls le Super Admin (0) et les Administrateurs de centre (1) peuvent accéder
    if (staffPrivilege != 0 && staffPrivilege != 1) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin ou un Administrateur peut voir la liste des médecins !");
    }

    // Vérifier si "center" est null ou vide
    int[] centers = (center != null && !center.isEmpty())
        ? Arrays.stream(center.split(",")).mapToInt(Integer::parseInt).toArray()
        : new int[0]; // Tableau vide si aucun centre n'est fourni

    // Vérifier si "morning" est null avant d'appeler contains()
    boolean isMorning = morning != null && morning.contains("0");

    int workTimeId = WorkTimeService.getId(day, isMorning);
    List<Staff> doctors = service.findAllFree(centers, workTimeId);

    return ResponseEntity.ok(doctors);
    }

    // Ajouter un membre du staff (Super Admin ou Admin)
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff newStaff = service.saveStaff(staff);
        return ResponseEntity.ok(newStaff);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int id, @RequestBody Staff staff) {
        try {
            Staff updatedStaff = service.updateStaff(id, staff);
            return ResponseEntity.ok(updatedStaff);
        } catch (StaffNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retourne 404 si le staff n'existe pas
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        service.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer tous les médecins d'un centre spécifique
    @GetMapping("/doctors/center/{centerId}")
    public List<Staff> findDoctorsByCenter(@PathVariable int centerId) {
    return service.findDoctorsByCenter(centerId);
    }

    // Récupérer tous les administrateurs
    @GetMapping("/admins")
    public List<Staff> findAllAdmins() {
    return service.findAllAdmins();
    }
}
