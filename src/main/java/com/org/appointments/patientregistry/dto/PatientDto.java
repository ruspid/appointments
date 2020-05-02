package com.org.appointments.patientregistry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PatientDto {
    @NonNull public final String id;
    @NonNull public final String firstName;
    @NonNull public final String secondName;
    @NonNull public final String address;
}
