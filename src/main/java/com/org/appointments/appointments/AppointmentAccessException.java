package com.org.appointments.appointments;

class AppointmentAccessException extends RuntimeException {
    AppointmentAccessException(){
        super("no access for editing appointment");
    }
}
