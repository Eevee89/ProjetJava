package org.example.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

import org.example.Entities.Center;
import org.example.Entities.AuthHeader;
import org.example.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

@RestController
public class CenterRestController {
    
    @Autowired
    private CenterService service;

    @GetMapping("api/centers")
    public List<Center> findAll() {
        return service.findAll();
    }

    @GetMapping("api/centers/cities")
    public List<String> findAllCities(@RequestHeader("Custom-Auth") String userDatas) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(userDatas, AuthHeader.class);

        System.out.println("\n\n ----- \n\n");
        System.out.println(datas.email);
        System.out.println(datas.password);
        System.out.println("\n\n ----- \n\n");
        
        return service.getAllCities();
    }
}
