package com.org.appointments.appointments.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;


@Builder
@Value
public class AppointmentDto {
    @NonNull public String appointmentId;
    @NonNull public String doctorId;
    @NonNull public String patientId;
    @NonNull public String cabinetId;
    @NonNull public LocalDateTime appointmentTime;
}
