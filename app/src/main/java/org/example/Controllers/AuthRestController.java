package org.example.Controllers;

import java.util.List;
import org.example.Entities.AuthHeader;
import org.example.Entities.Center;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
    @Autowired
    private AuthService service;

    @GetMapping("api/auth")
    public ResponseEntity auth(@RequestHeader("Custom-Auth") String userDatas) {
        boolean isAuth = service.authentify(userDatas);
        if (!isAuth) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String name = service.findNameByEmail(userDatas);
        return ResponseEntity.ok(String.format("{\"name\":\"%s\"}", name));
    }
}
