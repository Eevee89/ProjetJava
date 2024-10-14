package org.example.Entities;

import java.util.Date;

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
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center centerId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Staff doctorId; // Assuming "doctor" is a type of "Staff"

    @Column(nullable = false)
    private Date time;

    public Appointment(int id, Center centerId, Patient patientId, Staff doctorId, Date time) {
        this.id = id;
        this.centerId = centerId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.time = time;
    }

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Center getCenterId() {
        return centerId;
    }

    public void setCenterId(Center centerId) {
        this.centerId = centerId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Staff getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Staff doctorId) {
        this.doctorId = doctorId;
    }

    public Date gettime() {
        return time;
    }

    public void settime(Date time) {
        this.time = time;
    }
}