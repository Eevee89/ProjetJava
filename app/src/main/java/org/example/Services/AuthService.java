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

        Patient patient = repository.findByEmail(datas.email);
        // Vérifier si le patient existe
        if (patient == null) { 
            return false;
        }

        String password = repository.findPasswordWithEmail(datas.email);

        if (!password.equals(datas.password)) {
            return false;
        }

        return true;
    }
}