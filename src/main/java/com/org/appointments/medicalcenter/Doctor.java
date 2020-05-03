package com.org.appointments.medicalcenter;


import com.org.appointments.medicalcenter.dto.DoctorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
class Doctor {

    @Getter
    @Setter
    private String id;
    private String firstName;
    private String secondName;
    private Specialization specialization;

    DoctorDto dto() {
        return DoctorDto.builder()
                .firstName(firstName)
                .id(id).secondName(secondName)
                .firstName(firstName)
                .specialization(specialization)
                .build();
    }
}
