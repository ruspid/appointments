package com.org.appointments.appointments;

import java.util.Optional;

public interface AppointmentsRepository {

    String addAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment dto);
    void cancelAppointment(String id);
    Optional<Appointment> readAppointment(String id);

}
