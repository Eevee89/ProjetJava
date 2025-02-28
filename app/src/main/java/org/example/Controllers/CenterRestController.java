package org.example.Controllers;

import java.util.List;
import org.example.Entities.Center;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Services.AuthService;
import org.example.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centers")
public class CenterRestController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CenterService centerService;

    @GetMapping("api/centers")
    public List<Center> findAll(@RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        return service.findAll();
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UnauthentifiedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

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
