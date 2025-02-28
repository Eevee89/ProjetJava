package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

import org.example.Entities.Center;
import org.example.Entities.Staff;
import org.example.Entities.AuthHeader;
import org.example.Services.CenterService;
import org.example.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centers")
public class CenterRestController {

    @Autowired
    private CenterService centerService;

    @Autowired
    private StaffService staffService;

    // Lister tous les centres
    @GetMapping
    public List<Center> findAll() {
        return centerService.findAll();
    }

    @GetMapping("/cities")
    public List<String> findAllCities(@RequestHeader("Custom-Auth") String userDatas) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);

        System.out.println("\n\n ----- \n\n");
        System.out.println(datas.email);
        System.out.println(datas.password);
        System.out.println("\n\n ----- \n\n");
        
        return centerService.getAllCities();
    }

    // Créer un centre
    @PostMapping
    public ResponseEntity<?> createCenter(
        @RequestBody Center center,
        @RequestHeader("Custom-Auth") String userDatas
    ) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
        boolean isCorrect = staffService.auth(datas.email, datas.password);
        if (!isCorrect) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
        }

        // Récupérer le staff associé à l'email
        Staff staff = staffService.findByEmail(datas.email);
        if (staff == null) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
        }

        // Vérifier si l'utilisateur est Super Admin (privilège 0)
        if (staff.getPrivilege() != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin a l'autorisation");
        }

        Center newCenter = centerService.saveCenter(center);
        return ResponseEntity.ok(newCenter);
    }


    // Modifier un centre (seulement pour le Super Admin)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCenter(
            @PathVariable int id,
            @RequestBody Center updatedCenter,
            @RequestHeader("Custom-Auth") String userDatas
    ) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
        boolean isCorrect = staffService.auth(datas.email, datas.password);
        if (!isCorrect) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
        }

        // Récupérer le staff associé à l'email
        Staff staff = staffService.findByEmail(datas.email);
        if (staff == null) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
        }

        // Vérifier si l'utilisateur est Super Admin (privilège 0)
        if (staff.getPrivilege() != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin a l'autorisation");
        }

        Center center = centerService.updateCenter(id, updatedCenter);
        return center != null ? ResponseEntity.ok(center) : ResponseEntity.notFound().build();
    }
    
    // Supprimer un centre (seulement pour le Super Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCenter(
            @PathVariable int id,
            @RequestHeader("Custom-Auth") String userDatas
    ) { 
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
        boolean isCorrect = staffService.auth(datas.email, datas.password);
        if (!isCorrect) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
        }

        // Récupérer le staff associé à l'email
        Staff staff = staffService.findByEmail(datas.email);
        if (staff == null) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
        }

        // Vérifier si l'utilisateur est Super Admin (privilège 0)
        if (staff.getPrivilege() != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin a l'autorisation");
        }

        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
