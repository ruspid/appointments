package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;

interface AppointmentFacade {
    String scheduleAppointment(AppointmentFormDto appointmentDto);
    AppointmentDto rescheduleAppointment(RescheduleAppointmentApplication dto);
    void cancelAppointment(CancelAppointmentApplication dto);
    AppointmentDto readAppointment(String id);

}