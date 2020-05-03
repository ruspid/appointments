package com.org.appointments.medicalcenter.dto;

import com.org.appointments.medicalcenter.Specialization;
import lombok.Value;

@Value
public class DoctorRegistrationDto {
    public final String firstName;
    public final String secondName;
    public final Specialization specialization;

}
