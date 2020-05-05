package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.*;

import java.util.stream.IntStream;

class PatientQueryRepositoryTest {

    private PatientConfiguration config;
    private PatientQueryRepository patientQueryRepository;
    private PatientFacade patientFacade;

    private PatientRegistrationDto generateDto(Integer i) {
        return new PatientRegistrationDto(i.toString(), i.toString(), i.toString());
    }

    @BeforeEach
    void initializeTestSet() {
        config = new PatientConfiguration();
        patientQueryRepository = config.patientQueryRepository();
        patientFacade = config.patientService();

    }

    @Test
    void shouldReturnPageOfPatientRecords() {
        //given
        int numberOfPatients = 20;
        IntStream.range(0, numberOfPatients)
                .mapToObj(this::generateDto)
                .forEach(patientFacade::registerPatient);
        //and
        int pageNumber = 1;
        int recordPerPage = 10;
        Pageable pageRequest = PageRequest.of(pageNumber, recordPerPage);
        //when
        Page<PatientDto> patients = patientQueryRepository.readAllPatients(pageRequest);
        //then
        Assertions.assertEquals(recordPerPage, patients.get().count());
        Assertions.assertEquals(numberOfPatients, patients.getTotalElements());
    }

    @Test
    void shouldFindMatchingPatients() {
        //given
        patientFacade.registerPatient(new PatientRegistrationDto("John", "Wick", "NY"));
        patientFacade.registerPatient(new PatientRegistrationDto("Wiz", "Cudi", "LA"));
        patientFacade.registerPatient(new PatientRegistrationDto("Joyner", "Lucas", "AT"));

        int numberOfPages = 1;
        int recordPerPage = 10;
        Pageable pageRequest = PageRequest.of(numberOfPages, recordPerPage);
        //when
        Page<PatientDto> patients = patientQueryRepository.searchPatients("wi", pageRequest);
        //then
        Assertions.assertEquals(2, patients.get().count());
    }


}