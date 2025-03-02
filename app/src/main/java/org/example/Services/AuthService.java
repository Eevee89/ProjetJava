package org.example.Services;

import org.example.Entities.Staff;
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

<<<<<<< HEAD
        Patient patient = repository.findByEmail(datas.email);
        // Vérifier si le patient existe
        if (patient == null) { 
            return false;
        }

        String password = repository.findPasswordWithEmail(datas.email);
=======
        int count = 0;
        if (datas.role.equals("USER")) {
            count = patientRepository.countByEmail(datas.email);
        }

        if (datas.role.equals("STAFF")){
            count = staffRepository.countByEmail(datas.email);
        }
        
        if (count == 0) {
            return false;
        }

        String password = "";

        if (datas.role.equals("USER")) {
            password = patientRepository.findPasswordWithEmail(datas.email);
        }
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)

        if (datas.role.equals("STAFF")) {
            password = staffRepository.findPasswordWithEmail(datas.email);
        }

        if (!password.equals(datas.password)) {
            return false;
        }

        return true;
    }
<<<<<<< HEAD
}
=======

    public String findNameByEmail(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
        return patientRepository.findNameByEmail(datas.email);
    }

    public Integer findIdByEmail(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
        return patientRepository.findIdByEmail(datas.email);
    }

    public boolean isSuperAdmin(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);

        if (datas.role.equals("USER")) {
            return false;
        }

        int privilege = -1;
        if (datas.role.equals("STAFF")){
            Staff staff = staffRepository.findStaffByEmail(datas.email);
            privilege = staff.getPrivilege();
        }
        if (privilege != 0){
            return false;
        }
        return true;
    }

    public boolean isAdmin(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);

        if (datas.role.equals("USER")) {
            return false;
        }

        int privilege = -1;
        if (datas.role.equals("STAFF")){
            Staff staff = staffRepository.findStaffByEmail(datas.email);
            privilege = staff.getPrivilege();
        }
        if (privilege != 1){
            return false;
        }
        return true;
    }
}
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
