package org.example.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String phone;

    @Column
    private String name;

    @ManyToMany(mappedBy = "centers")
    private List<Staff> staff;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Center(Integer id, String phone, Address address, String name) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.name = name;
    }

    public Center() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }
}