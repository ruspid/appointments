package com.org.appointments.appointments;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;


@Value
@AllArgsConstructor
class RescheduleAppointmentApplication{
    public String appointmentId;
    public Applicant applicant;
    public LocalDateTime newAppointmentTime;
}
