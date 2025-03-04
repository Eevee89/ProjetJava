package org.example.Entities;

import java.util.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Staff medecin;

    @ManyToOne
    private Center center;

    private LocalDateTime date;

    public Appointment(int id, Center centerId, Patient patientId, Staff doctorId, Date time) {
        this.id = id;
        this.center = centerId;
        this.patient = patientId;
        this.medecin = doctorId;
        this.date = time.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getDoctor() {
        return medecin;
    }

    public void setDoctor(Staff doctor) {
        this.medecin = doctor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}