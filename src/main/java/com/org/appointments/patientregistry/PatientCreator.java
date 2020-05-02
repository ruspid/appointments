package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;

class PatientCreator {
    static Patient from(PatientDto dto){
        return Patient.builder()
                .address(dto.address)
                .firstName(dto.firstName)
                .id(dto.id)
                .secondName(dto.secondName)
                .build();
    }

    static Patient from(PatientRegistrationDto dto){
        return Patient.builder()
                .address(dto.address)
                .firstName(dto.firstName)
                .secondName(dto.secondName)
                .build();
    }
}
