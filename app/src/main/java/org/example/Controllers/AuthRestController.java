package org.example.Controllers;

import org.example.Entities.Staff;
import org.example.Exceptions.UnauthentifiedException;
import org.example.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
    @Autowired
    private AuthService service;

    @GetMapping("api/auth")
    public ResponseEntity<String> auth(@RequestHeader("Custom-Auth") String userDatas) {
        boolean isAuth = service.authentify(userDatas);
        if (!isAuth) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String name = service.findNameByEmail(userDatas);
        boolean isStaff = service.isStaff(userDatas);
        Integer privilegeLevel = null;

        if (isStaff) {
            Staff staff = service.findStaffByEmail(userDatas);
            privilegeLevel = staff.getPrivilege();
            return ResponseEntity.ok(String.format(
                "{\"name\":\"%s\",\"privilegeLevel\":%d,\"isStaff\":true}", 
                name, 
                privilegeLevel
            ));
        }

        return ResponseEntity.ok(String.format(
            "{\"name\":\"%s\",\"isStaff\":false}", 
            name
        ));
    }
}
