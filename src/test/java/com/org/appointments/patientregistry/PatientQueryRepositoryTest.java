package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.data.domain.*;

import java.util.stream.IntStream;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientQueryRepositoryTest {

    private final PatientConfiguration config = new PatientConfiguration();
    private final PatientQueryRepository patientQueryRepository = config.patientQueryRepository();
    private final PatientFacade patientFacade = config.patientService();

    private PatientRegistrationDto generateDto(Integer i){
        return new PatientRegistrationDto(i.toString(), i.toString(), i.toString());
    }
    @BeforeAll
    void initializeTestSet(){
        IntStream.of(20)
                .mapToObj(this::generateDto)
                .forEach(patientFacade::registerPatient);
    }

    @Test
    void shouldReturnPageOfPatientRecords() {
        //given test set
        int numberOfPages = 1;
        int recordPerPage = 10;
        Pageable pageRequest = PageRequest.of(numberOfPages, recordPerPage);
        //when
        Page<PatientDto> patients = patientQueryRepository.readAllPatients(pageRequest);
        //then
        Assertions.assertEquals(recordPerPage, patients.getTotalElements());
        Assertions.assertEquals(numberOfPages, patients.getTotalPages());
    }



}