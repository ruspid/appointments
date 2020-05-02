package com.org.appointments.patientregistry;

import java.util.Optional;

public interface PatientRepository{

    Patient addPatientRecord(Patient patient);

    Optional<Patient> searchPatient(String id);

    void deletePatientRecord(String id);

    Patient updatePatientRecord(Patient patient);

}
