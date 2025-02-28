package org.example.Controllers;

import java.util.List;
import org.example.Entities.Center;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Services.AuthService;
import org.example.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CenterRestController {
    
    @Autowired
    private CenterService service;

    @Autowired
    private AuthService authService;

    @GetMapping("api/centers")
    public List<Center> findAll(@RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {
        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }
        
        return service.findAll();
    }

    @GetMapping("api/centers/cities")
    public List<String> findAllCities(@RequestHeader("Custom-Auth") String userDatas) throws UnauthentifiedException {

        boolean isAuth = authService.authentify(userDatas);
        if (!isAuth) {
            throw new UnauthentifiedException();
        }

        return service.getAllCities();
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UnauthentifiedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
