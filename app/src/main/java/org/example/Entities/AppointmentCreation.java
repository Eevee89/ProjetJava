package org.example.Entities;

import java.util.Date;

public class AppointmentCreation {
    public int centerId;
    public int doctorId;
    public int patientId;
    public Date time;

    public AppointmentCreation(int centerId, int doctorId, int patientId, Date time) {
        this.centerId = centerId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.time = time;
    }

    public AppointmentCreation() { }
}
