package org.example.Services;


import org.example.Entities.Staff;
import com.google.gson.Gson;
import org.example.Entities.Patient;
import org.example.Repositories.PatientRepository;
import org.example.Entities.AuthHeader;
import org.example.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    public boolean authentify(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return false;
        }

        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
      
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

        if (datas.role.equals("STAFF")) {
            password = staffRepository.findPasswordWithEmail(datas.email);
        }

        if (!password.equals(datas.password)) {
            return false;
        }

        return true;
    }

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

    public boolean isStaff(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);

        if (datas.role.equals("STAFF")) {
            return true;
        }
        return false;
    }

}
