package org.example.Services;

import com.google.gson.Gson;
import org.example.Entities.AuthHeader;
import org.example.Entities.Patient;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PatientRepository repository;

    public boolean authentify(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
        int count = repository.findByEmail(datas.email);
        
        if (count == 0) {
            return false;
        }

        String password = repository.findPasswordWithEmail(datas.email);

        System.out.println(password);

        if (!password.equals(datas.password)) {
            return false;
        }

        return true;
    }
}
