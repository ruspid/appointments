package com.org.appointments.medicalcenter;

class DoctorNotFoundException extends RuntimeException {
    DoctorNotFoundException(String id) {
        super("No doctor found with id " + id);
    }
}
