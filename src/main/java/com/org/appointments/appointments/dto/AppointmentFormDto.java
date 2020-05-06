package com.org.appointments.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor
public class AppointmentFormDto {
    @NonNull public String doctorId;
    @NonNull public String patientId;
    @NonNull public LocalDateTime appointmentTime;
}
