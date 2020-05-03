package com.org.appointments.medicalcenter;


import com.org.appointments.medicalcenter.dto.DoctorDto;
import com.org.appointments.medicalcenter.dto.DoctorRegistrationDto;

class DoctorCreator {
    static Doctor from(DoctorDto dto){
        return Doctor.builder()
                .specialization(dto.specialization)
                .firstName(dto.firstName)
                .id(dto.id).secondName(dto.secondName)
                .build();
    }

    static Doctor from(DoctorRegistrationDto dto){
        return Doctor.builder()
                .specialization(dto.specialization)
                .firstName(dto.firstName)
                .secondName(dto.secondName)
                .build();
    }
}
