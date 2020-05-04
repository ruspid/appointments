package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;

class AppointmentCreator {
    static Appointment from(AppointmentDto dto) {
        return Appointment.builder()
                .appointmentTime(dto.appointmentTime)
                .doctorId(dto.doctorId)
                .patientId(dto.patientId)
                .build();
    }

    static Appointment from(AppointmentFormDto dto) {
        return Appointment.builder()
                .doctorId(dto.doctorId)
                .patientId(dto.patientId)
                .appointmentTime(dto.appointmentTime)
                .build();
    }
}
