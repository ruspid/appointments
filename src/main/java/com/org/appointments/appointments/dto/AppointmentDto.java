package com.org.appointments.appointments.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;


@Builder
@Value
public class AppointmentDto {
    public String appointmentId;
    public String doctorId;
    public String patientId;
    public String cabinetId;
    public LocalDateTime appointmentTime;
}
