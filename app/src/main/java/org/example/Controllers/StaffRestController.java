package org.example.Controllers;

import java.util.List;
import java.util.Arrays;

import org.example.Entities.Staff;
import org.example.Services.StaffService;
import org.example.Services.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffRestController {
    
    @Autowired
    private StaffService service;

    @GetMapping("api/doctors")
    public List<Staff> findAll(@RequestParam(name = "centers", required = false)String center,
                                @RequestParam(name = "day", required = false)String day,
                                @RequestParam(name = "morning", required = false)String morning) {
        
        int[] centers = Arrays.stream(center.split(",")).mapToInt(Integer::parseInt).toArray();
        int workTimeId = WorkTimeService.getId(day, morning.contains("0"));
        return service.findAllFree(centers, workTimeId);
    }
}
