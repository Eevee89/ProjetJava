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
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Staff doctor;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(name = "time")
    private LocalDateTime time;

    public Appointment(int id, Center centerId, Patient patientId, Staff doctorId, Date time) {
        this.id = id;
        this.center = centerId;
        this.patient = patientId;
        this.doctor = doctorId;
        this.time = time.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
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
        return doctor;
    }

    public void setDoctor(Staff doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}