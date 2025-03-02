package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

<<<<<<< HEAD
import org.example.Entities.AuthHeader;
=======
import org.example.Services.AuthService;
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
import org.springframework.web.util.UriBuilder;
import com.google.gson.Gson;
=======
import org.example.Exceptions.UnauthentifiedException;
import org.example.Exceptions.UnauthorizedException;
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)

@RestController
public class StaffRestController {
    
    @Autowired
    private StaffService service;

    @Autowired
    private AuthService authService;

    // Avoir la liste des médecins
    @GetMapping("/doctors")
    public ResponseEntity<?> findAll(
        @RequestParam(name = "centers", required = false) String center,
        @RequestParam(name = "day", required = false) String day,
<<<<<<< HEAD
        @RequestParam(name = "morning", required = false) String morning
    ){
=======
        @RequestParam(name = "morning", required = false) String morning,
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {

        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)

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
    @PostMapping("/staff")
<<<<<<< HEAD
    public ResponseEntity<String> createStaff(
        @RequestBody Staff staff,
        @RequestHeader("Custom-Auth") String userDatas) {
            Gson gson = new Gson();
            AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
            boolean isCorrect = service.auth(datas.email, datas.password);
            if (!isCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
            }
    
            // Récupérer le staff associé à l'email
            Staff adminStaff = service.findByEmail(datas.email);
            if (adminStaff == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
            }
    
            if (staff.getPrivilege() != 0 && staff.getPrivilege() != 1) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin ou un Administrateur a l'autorisation");
            }
        
        
=======
    public ResponseEntity<Staff> createStaff(
        @RequestBody Staff staff,
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
    
        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        boolean isAdmin = authService.isAdmin(userDatas);
        if (!isSuperAdmin && !isAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin ou admin pour utiliser cette fonctionnalité");
        }

>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
        Staff newStaff = service.saveStaff(staff);
        return ResponseEntity.ok("Nouveau Staff crée");
    }

    // Modifier les informations d'un Staff (Admin ou SuperAdmin)
    @PutMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<String> updateStaff(
        @PathVariable int id,
        @RequestBody Staff newStaff,
        @RequestHeader("Custom-Auth") String userDatas) {

            Gson gson = new Gson();
            AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
            boolean isCorrect = service.auth(datas.email, datas.password);
            if (!isCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
            }
    
            // Récupérer le staff associé à l'email
            Staff staff = service.findByEmail(datas.email);
            if (staff == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
            }
    
            if (staff.getPrivilege() != 0 && staff.getPrivilege() != 1) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin ou un Administrateur a l'autorisation");
            }

=======
    public ResponseEntity<Staff> updateStaff(
        @PathVariable int id,
        @RequestBody Staff staff,
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
    
        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        boolean isAdmin = authService.isAdmin(userDatas);
        if (!isSuperAdmin && !isAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin ou admin pour utiliser cette fonctionnalité");
        }
        
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
        try {
            Staff updatedStaff = service.updateStaff(id, newStaff);
            return ResponseEntity.ok("Mise à jour réussie !");
        } catch (StaffNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retourne 404 si le staff n'existe pas
        }
    }

    // Supprimer un Staff (SuperAdmin)
    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<String> deleteStaff(
        @PathVariable int id,
        @RequestHeader("Custom-Auth") String userDatas) {

            Gson gson = new Gson();
            AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);
            boolean isCorrect = service.auth(datas.email, datas.password);
            if (!isCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentification échouée");
            }
    
            // Récupérer le staff associé à l'email
            Staff staff = service.findByEmail(datas.email);
            if (staff == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non trouvé");
            }
    
            if (staff.getPrivilege() != 0) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Seul un Super Admin");
            }
=======
    public ResponseEntity<Void> deleteStaff(
        @PathVariable int id,
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        if (!isSuperAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin pour utiliser cette fonctionnalité");
        }
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)

        service.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer tous les administrateurs
    @GetMapping("/admins")
    public List<Staff> findAllAdmins(
        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {

        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        boolean isAdmin = authService.isAdmin(userDatas);
        if (!isSuperAdmin && !isAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin ou admin pour utiliser cette fonctionnalité");
        }
        
    return service.findAllAdmins();
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
