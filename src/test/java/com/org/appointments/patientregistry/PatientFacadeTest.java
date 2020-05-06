package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class PatientFacadeTest {

    private final PatientConfiguration patientConfiguration = new PatientConfiguration();
    private PatientFacade facade;

    @BeforeEach
    void reset() {
        this.facade = patientConfiguration.patientService();
    }

    @Test
    void shouldRegisterPatient() {
        //given
        PatientRegistrationDto dto = new PatientRegistrationDto("John", "Wick", "NY");
        //when
        String patientId = facade.registerPatient(dto);
        //then
        assertTrue(StringUtils.isNotBlank(patientId));
    }

    @Test
    void shouldFindPatientById() {
        //given
        PatientRegistrationDto dto = new PatientRegistrationDto("John", "Wick", "NY");
        String patientId = facade.registerPatient(dto);
        PatientDto expectedPatient = new PatientDto(patientId, dto.firstName, dto.secondName, dto.address);
        //when
        PatientDto patientDto = facade.searchPatient(patientId);
        //then
        assertEquals(expectedPatient, patientDto);
    }

    @Test
    void shouldDeletePatientRecord() {
        //given
        PatientRegistrationDto dto = new PatientRegistrationDto("John", "Wick", "NY");
        String patientId = facade.registerPatient(dto);
        //when
        facade.deletePatientRecord(patientId);
        //then
        assertThrows(PatientNotFoundException.class, () -> facade.searchPatient(patientId));
    }

    @Test
    void shouldUpdatePatientInformation() {
        //given
        PatientRegistrationDto registrationDto = new PatientRegistrationDto("John", "Wick", "NY");
        String patientId = facade.registerPatient(registrationDto);
        PatientDto patientDto = new PatientDto(patientId, "John", "Wick", "LA");
        //when
        facade.updatePatientRecord(patientDto);
        //then
        assertEquals(facade.searchPatient(patientId), patientDto);
    }


}