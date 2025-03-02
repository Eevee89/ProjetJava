package org.example.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

<<<<<<< HEAD
    @ManyToMany
=======
    @ManyToMany(fetch = FetchType.LAZY)
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
    @JoinTable(name = "staff_centers",
               joinColumns = @JoinColumn(name = "staff_id"),
               inverseJoinColumns = @JoinColumn(name = "center_id"))
    private List<Center> centers;

    private int privilege;

    @ManyToMany
    @JoinTable(name = "staff_work_times",
               joinColumns = @JoinColumn(name = "staff_id"),
               inverseJoinColumns = @JoinColumn(name = "work_time_id"))
    private List<WorkTime> workTimes;

    public Staff(int id, String firstName, String lastName, String phone, int privilege) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.privilege = privilege;
    }

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

<<<<<<< HEAD
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
=======
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return email;
    }
    
    public void setPassword(String password){
>>>>>>> 58b39e1 (Implementation of privilege checks and addition of 2 unit tests)
        this.password = password;
    }
}