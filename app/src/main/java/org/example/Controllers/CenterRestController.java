package org.example.Controllers;


import org.example.Entities.Center;
import org.example.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterRestController {

    @Autowired
    private CenterService centerService;

    // Créer un centre
    @PostMapping
    public ResponseEntity<?> createCenter(
        @RequestBody Center center,
        @RequestHeader("staffPrivilege") int staffPrivilege // Récupérer le privilège depuis les headers
    ) {
    // Vérifier si l'utilisateur est Super Admin
    if (staffPrivilege != 0) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin a l'autorisation");
    }
    Center newCenter = centerService.saveCenter(center);
    return ResponseEntity.ok(newCenter);
    }



    // Lister tous les centres
    @GetMapping
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }

    // Modifier un centre (seulement pour le Super Admin)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCenter(
            @PathVariable int id,
            @RequestBody Center updatedCenter,
            @RequestHeader("staffPrivilege") int staffPrivilege // Privilège de l'utilisateur
    ) {
        // Vérifier si l'utilisateur est Super Admin
        if (staffPrivilege != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin peut modifier un centre.");
        }

        Center center = centerService.updateCenter(id, updatedCenter);
        return center != null ? ResponseEntity.ok(center) : ResponseEntity.notFound().build();
    }
    
    // Supprimer un centre (seulement pour le Super Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCenter(
            @PathVariable int id,
            @RequestHeader("staffPrivilege") int staffPrivilege // Privilège de l'utilisateur
    ) {
        // Vérifier si l'utilisateur est Super Admin
        if (staffPrivilege != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin peut supprimer un centre.");
        }

        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
