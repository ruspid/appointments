package com.org.appointments.appointments;



import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;

import java.time.LocalDateTime;

interface  AppointmentCommandService {
    String scheduleAppointment(AppointmentFormDto appointmentDto);
    AppointmentDto rescheduleAppointment(LocalDateTime newDate, String id);
    void cancelAppointment(String id);
    AppointmentDto readAppointment(String id);
}