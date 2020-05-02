package com.org.appointments.patientregistry.dto;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class PatientRegistrationDto {
    @NonNull public final String firstName;
    @NonNull public final String secondName;
    @NonNull public final String address;
}
