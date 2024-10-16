package org.example.Services;

import org.example.Entities.Center;
import org.example.Exceptions.CenterNotFoundException;
import org.example.Repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {
    
    @Autowired
    private CenterRepository repository;

    public Center findOne(int id) throws CenterNotFoundException {
        return repository.findById(id).orElseThrow(CenterNotFoundException::new);
    }
}
