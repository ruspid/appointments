package com.org.appointments.medicalcenter;


import com.org.appointments.medicalcenter.dto.DoctorDto;
import com.org.appointments.medicalcenter.dto.DoctorRegistrationDto;

interface DoctorFacade {

    String registerDoctor(DoctorRegistrationDto doctorRegistrationForm);

    DoctorDto getDoctor(String id);

    void deleteDoctor(String id);

    DoctorDto updateDoctor(DoctorDto doctorDto);
}
