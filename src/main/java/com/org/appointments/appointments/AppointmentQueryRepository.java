package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentQueryRepository {
    Page<AppointmentDto> patientAppointments(String id, Pageable pageable);
    Page<AppointmentDto> scheduledAppointments(Pageable pageable);
}
