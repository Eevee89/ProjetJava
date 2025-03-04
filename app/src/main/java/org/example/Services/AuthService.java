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
      
        // Vérification pour STAFF
        if ("STAFF".equals(datas.role)) {
            Staff staff = staffRepository.findByEmail(datas.email);
            if (staff == null) {
                return false;
            }
            return staff.getPassword().equals(datas.password);
        }

        // Vérification pour USER
        if ("USER".equals(datas.role)) {
            int count = patientRepository.countByEmail(datas.email);
            if (count == 0) {
                return false;
            }
            String password = patientRepository.findPasswordWithEmail(datas.email);
            return password != null && password.equals(datas.password);
        }

        return false;
    }

    public String findNameByEmail(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
        
        if ("STAFF".equals(datas.role)) {
            Staff staff = staffRepository.findByEmail(datas.email);
            return staff.getFirstName() + " " + staff.getLastName();
        }
        
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
        return "STAFF".equals(datas.role);
    }

    public Staff findStaffByEmail(String jsonString) {
        Gson gson = new Gson();
        AuthHeader datas = gson.fromJson(jsonString, AuthHeader.class);
        return staffRepository.findByEmail(datas.email);
    }

}
