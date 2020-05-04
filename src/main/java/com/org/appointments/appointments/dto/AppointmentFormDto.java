package com.org.appointments.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
public class AppointmentFormDto {
    public String doctorId;
    public String patientId;
    public LocalDateTime appointmentTime;
}
