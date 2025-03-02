package org.example.Services;

import java.util.List;

import org.example.Entities.Staff;
import org.example.Exceptions.StaffNotFoundException;
import org.example.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class StaffService {

    @Autowired
    private StaffRepository repository;

    // Trouver tous les médecins disponibles selon les centres et le créneau de travail
    public List<Staff> findAllFree(int[] centers, int worktime) {
        System.out.println(worktime);
        List<Staff> fromCenter = repository.findByCenter(centers);
        List<Staff> freeAt = repository.findByFreeAt(worktime);
        fromCenter.removeIf(elt -> freeAt.contains(elt));
        return fromCenter;
    }

    // Trouver un médecin selon son id
    public Staff findOne(int id) throws StaffNotFoundException {
        return repository.findById(id)
            .orElseThrow(() -> new StaffNotFoundException("Staff not found with ID: " + id));
    } 

    // Ajouter un membre du staff
    public Staff saveStaff(Staff staff) {
        return repository.save(staff);
    }

    public Staff updateStaff(int id, Staff newStaffData) throws StaffNotFoundException {
        Staff staff = repository.findById(id)
            .orElseThrow(() -> new StaffNotFoundException("Staff not found with ID: " + id));
    
        staff.setFirstName(newStaffData.getFirstName());
        staff.setLastName(newStaffData.getLastName());
        staff.setPhone(newStaffData.getPhone());
        staff.setPrivilege(newStaffData.getPrivilege());
    
        return repository.save(staff);
    }

    // Supprimer un membre du staff
    public void deleteStaff(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id); // Suppression via JPA
        } else {
            throw new RuntimeException("Impossible de supprimer : Staff introuvable avec l'ID : " + id);
        }
    }
    public List<Staff> findDoctorsByCenter(int centerId) {
        return repository.findByPrivilegeAndCenters_Id(2, centerId);
    }
    
    public List<Staff> findAllAdmins() {
        return repository.findByPrivilege(1);
    }

    public Staff findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Vérification du mail et du mot de passe
    public boolean auth(String email, String password) { 
        // Récupérer le staff associé à l'email
        Staff staff = repository.findByEmail(email);

        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        return staff != null && staff.getPassword().equals(password);
    }
}

