package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;

interface PatientFacade {

    String registerPatient(PatientRegistrationDto patientRegistrationForm);
    PatientDto searchPatient(String id);
    void deletePatientRecord(String id);
    PatientDto updatePatientRecord(PatientDto patientDto);
}
