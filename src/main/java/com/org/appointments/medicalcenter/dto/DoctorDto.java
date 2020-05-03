package com.org.appointments.medicalcenter.dto;

import com.org.appointments.medicalcenter.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class DoctorDto {
    public final String id;
    public final String firstName;
    public final String secondName;
    public final Specialization specialization;
}
