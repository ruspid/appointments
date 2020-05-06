package com.org.appointments.medicalcenter;

import com.org.appointments.medicalcenter.dto.DoctorDto;
import com.org.appointments.medicalcenter.dto.DoctorRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class DoctorFacadeTest {

    private final MedicalCenterConfiguration medicalCenterConfiguration = new MedicalCenterConfiguration();
    private DoctorFacade doctorFacade;

    @BeforeEach
    void initDoctorFacade() {
        doctorFacade = medicalCenterConfiguration.doctorFacade();
    }

    @Test
    void shouldRegisterDoctor(){
        //give
        DoctorRegistrationDto doctorRegistrationDto = new DoctorRegistrationDto("doctor", "doc", Specialization.DENTIST);
        //when
        String doctorId = doctorFacade.registerDoctor(doctorRegistrationDto);
        assertTrue(StringUtils.isNotBlank(doctorId));
    }


    @Test
    void shouldFindDoctorById() {
        //given
        String firstName = "doctor";
        String secondName = "doc";
        Specialization specialization = Specialization.DENTIST;

        DoctorRegistrationDto doctorRegistrationDto = new DoctorRegistrationDto(firstName, secondName, specialization);
        String doctorId = doctorFacade.registerDoctor(doctorRegistrationDto);
        DoctorDto expectedDoctor = new DoctorDto(doctorId, firstName, secondName, specialization);
        //when
        DoctorDto doctorDto = doctorFacade.getDoctor(doctorId);
        //then
        assertEquals(expectedDoctor, doctorDto);
    }

    @Test
    void shouldDeletePatientRecord() {
        //given
        String firstName = "doctor";
        String secondName = "doc";
        Specialization specialization = Specialization.DENTIST;

        DoctorRegistrationDto doctorRegistrationDto = new DoctorRegistrationDto(firstName, secondName, specialization);
        String doctorId = doctorFacade.registerDoctor(doctorRegistrationDto);
        //when
        doctorFacade.deleteDoctor(doctorId);
        //then
        assertThrows(DoctorNotFoundException.class, () -> doctorFacade.getDoctor(doctorId));
    }

    @Test
    void shouldUpdatePatientInformation() {
        //given
        String firstName = "doctor";
        String secondName = "doc";
        Specialization specialization = Specialization.DENTIST;

        DoctorRegistrationDto doctorRegistrationDto = new DoctorRegistrationDto(firstName, secondName, specialization);
        String doctorId = doctorFacade.registerDoctor(doctorRegistrationDto);
        DoctorDto updatedRecord = new DoctorDto(doctorId, firstName, secondName, Specialization.NEUROLOGIST);
        //when
        doctorFacade.updateDoctor(updatedRecord);
        //then
        assertEquals(doctorFacade.getDoctor(doctorId), updatedRecord);
    }
}