package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.example.Entities.Address;
import org.example.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressRestController {
    @Autowired
    private AddressService service;

    @PostMapping(path = "api/address/create")
    public ResponseEntity<String> create(@RequestBody Address a) throws URISyntaxException{
        service.create(a);
        return ResponseEntity.created(new URI("api/address/"+a.getId())).body(String.format("{\"id\":%s}", a.getId()));
    }
}
