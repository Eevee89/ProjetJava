package org.example.Controllers;

import java.util.List;
import org.example.Entities.Center;
import org.example.Entities.Staff;
import org.example.Entities.AuthHeader;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Exceptions.UnauthorizedException;
import org.example.Services.AuthService;
import org.example.Services.CenterService;
import org.example.Services.StaffService;
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


    @Autowired
    private StaffService staffService;

   
    @GetMapping("api/centers")
    public List<Center> findAll(@RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        return centerService.getAllCenters();

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

        @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException{

        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        if (!isSuperAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin pour utiliser cette fonctionnalité");
        }

        Center newCenter = centerService.saveCenter(center);
        return ResponseEntity.ok(newCenter);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }


    // Modifier un centre (seulement pour le Super Admin)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCenter(
            @PathVariable int id,
            @RequestBody Center updatedCenter,
            @RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {

        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        boolean isSuperAdmin = authService.isSuperAdmin(userDatas);
        if (!isSuperAdmin) {
            throw new UnauthorizedException("L'utilisateur doit être Super Admin pour utiliser cette fonctionnalité");
        }

        Center center = centerService.updateCenter(id, updatedCenter);
        return center != null ? ResponseEntity.ok(center) : ResponseEntity.notFound().build();
    }
    
    // Supprimer un centre (seulement pour le Super Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCenter(
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

        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
