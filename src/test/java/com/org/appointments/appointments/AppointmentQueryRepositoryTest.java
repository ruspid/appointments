package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;

class AppointmentQueryRepositoryTest {


    private AppointmentsConfiguration configuration;
    private AppointmentFacade appointmentFacade;
    private AppointmentQueryRepository appointmentQueryRepository;

    @BeforeEach
    void resetTestData() {
        this.configuration = new AppointmentsConfiguration();
        this.appointmentFacade = configuration.appointmentCommandService();
        this.appointmentQueryRepository = configuration.appointmentQueryRepository();

    }

    @Test
    void shouldReadAllPatientAppointments() {
        //given
        String patientId = UUID.randomUUID().toString();
        String anotherPatient = "id";

        int numberOdPatientAppointments = 20;
        IntStream.range(0, numberOdPatientAppointments)
                .mapToObj(i -> this.patientAppointment(patientId))
                .forEach(appointmentFacade::scheduleAppointment);
        //and
        int numberOfOtherAppointments = 10;
        IntStream.range(0, numberOfOtherAppointments)
                .mapToObj(i -> this.patientAppointment(UUID.randomUUID().toString().substring(2)))//unique and different then @patientId
                .forEach(appointmentFacade::scheduleAppointment);

        int numberOfPages = 1;
        int recordPerPage = 10;
        Pageable pageRequest = PageRequest.of(numberOfPages, recordPerPage);
        //when
        Page<AppointmentDto> appointments = appointmentQueryRepository.patientAppointments(patientId, pageRequest);
        //then
        Assertions.assertEquals(numberOdPatientAppointments+numberOfOtherAppointments, appointments.getTotalElements());
        int expectedNumberOfPages = numberOdPatientAppointments / recordPerPage;
        Assertions.assertEquals(3, appointments.getTotalPages());

    }


    @Test
    void shouldReadAllAppointments() {
        //given
        int numberOfAppointments = 27;
        IntStream.range(0, numberOfAppointments)
                .mapToObj(i -> this.patientAppointment(UUID.randomUUID().toString().substring(1)))
                .forEach(appointmentFacade::scheduleAppointment);
        //and
        int numberOfPage = 1;
        int recordPerPage = 10;
        Pageable pageRequest = PageRequest.of(numberOfPage, recordPerPage);
        //when
        Page<AppointmentDto> appointments = appointmentQueryRepository.scheduledAppointments(pageRequest);
        //then
        Assertions.assertEquals(numberOfAppointments, appointments.getTotalElements());

        Assertions.assertEquals(3, appointments.getTotalPages());

    }

    private AppointmentFormDto patientAppointment(String id) {
        return new AppointmentFormDto(UUID.randomUUID().toString(), id, LocalDateTime.now());
    }


}