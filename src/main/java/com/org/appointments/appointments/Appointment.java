package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
class Appointment {

    private String appointmentId;
    private String cabinetId;
    private String doctorId;
    private String patientId;
    private LocalDateTime appointmentTime;


    AppointmentDto dto() {
        return AppointmentDto.builder()
                .doctorId(doctorId)
                .patientId(patientId)
                .appointmentTime(appointmentTime)
                .cabinetId(cabinetId)
                .build();
    }
}