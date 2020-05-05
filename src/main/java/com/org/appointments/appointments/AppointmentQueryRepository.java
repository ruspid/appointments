package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryRepository {
    Page<AppointmentDto> patientAppointments(String id, Pageable pageable);
    Page<AppointmentDto> scheduledAppointments(Pageable pageable);
    List<AppointmentDto> patientAppointments(String id);
    Optional<Appointment> readAppointment(String id);


}
