package org.example.Services;

import java.util.List;

import org.example.Entities.Staff;
import org.example.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository repository;

    public List<Staff> findAllFree(int[] centers, int worktime) {
        System.out.println(worktime);
        List<Staff> fromCenter = repository.findByCenter(centers);
        List<Staff> freeAt = repository.findByFreeAt(worktime);
        fromCenter.removeIf(elt -> freeAt.contains(elt));
        return fromCenter;
    }
}
