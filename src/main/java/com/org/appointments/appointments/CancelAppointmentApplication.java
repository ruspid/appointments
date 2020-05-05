package com.org.appointments.appointments;

import lombok.AllArgsConstructor;
import lombok.Value;


@Value
@AllArgsConstructor
class CancelAppointmentApplication {
    public String appointmentId;
    public Applicant applicant;
    public String applicantId;
}
