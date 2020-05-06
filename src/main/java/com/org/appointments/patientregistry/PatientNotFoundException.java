package com.org.appointments.patientregistry;

class PatientNotFoundException extends RuntimeException{
    PatientNotFoundException(String id) {
        super("No patient found with id " + id);
    }
}
