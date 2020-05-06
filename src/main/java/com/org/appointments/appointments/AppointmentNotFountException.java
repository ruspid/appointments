package com.org.appointments.appointments;

class AppointmentNotFountException extends RuntimeException {
    AppointmentNotFountException(String appointmentId) {
        super("No appointment with found with id " + appointmentId);
    }
}
