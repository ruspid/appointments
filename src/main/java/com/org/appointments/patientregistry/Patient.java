package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
class Patient {

    @Getter
    @Setter
    String id;
    String firstName;
    String secondName;
    String address;

    PatientDto dto() {
        return PatientDto.builder().id(id)
                .address(address)
                .firstName(firstName)
                .secondName(secondName)
                .build();
    }
}
