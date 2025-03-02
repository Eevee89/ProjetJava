package org.example.Services;

import org.example.Entities.Address;
import org.example.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public void create(Address a){
        repository.save(a);
    }
}
