package org.example.Controllers;

import org.example.Entities.Center;
import org.example.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterRestController {

    @Autowired
    private CenterService centerService;

    // Créer un centre
    @PostMapping
    public ResponseEntity<Center> createCenter(@RequestBody Center center) {
        Center newCenter = centerService.saveCenter(center);
        return ResponseEntity.ok(newCenter);
    }

    // Lister tous les centres
    @GetMapping
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }

    // Modifier un centre
    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center updatedCenter) {
        Center center = centerService.updateCenter(id, updatedCenter);
        return center != null ? ResponseEntity.ok(center) : ResponseEntity.notFound().build();
    }

    // Supprimer un centre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable int id) {
    centerService.deleteCenter(id);
    return ResponseEntity.noContent().build();
}
}
