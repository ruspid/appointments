package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentsRepository appointmentsRepository;

    @Override
    public String scheduleAppointment(AppointmentFormDto appointmentDto) {
        return appointmentsRepository.addAppointment(AppointmentCreator.from(appointmentDto));
    }

    @Override
    public AppointmentDto rescheduleAppointment(LocalDateTime newDate, String id) {
        return null;
    }

    @Override
    public void cancelAppointment(String id) {

    }

    @Override
    public AppointmentDto readAppointment(String id) {
        return null;
    }
}
