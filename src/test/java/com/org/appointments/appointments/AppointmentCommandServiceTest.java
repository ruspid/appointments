package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDateTime;

class AppointmentCommandServiceTest {

    private AppointmentCommandService appointmentCommandService;
    private AppointmentQueryRepository appointmentQueryRepository;

    @BeforeEach
    void init() {
        AppointmentsConfiguration configuration = new AppointmentsConfiguration();
        this.appointmentCommandService = configuration.appointmentCommandService();
        this.appointmentQueryRepository = configuration.appointmentQueryRepository();
    }

    @Test
    void shouldScheduleAppointment() {
        //given
        AppointmentFormDto dto = new AppointmentFormDto("doctor", "patient", LocalDateTime.now());
        //when
        String appointmentId = appointmentCommandService.scheduleAppointment(dto);
        //then
        Assertions.assertTrue(StringUtils.isNotBlank(appointmentId));
    }

    @Test
    void shouldCancelAppointmentBeingPatient() {
        //given
        String patientId = "id";
        String appointmentId = appointmentCommandService.scheduleAppointment(new AppointmentFormDto("doctor", patientId, LocalDateTime.now()));
        //when
        appointmentCommandService.cancelAppointment(new CancelAppointmentApplication(appointmentId, Applicant.PATIENT, patientId));
        //then
        Assertions.assertTrue(appointmentQueryRepository.readAppointment(appointmentId).isEmpty());
    }


    @Test
    void shouldCancelAppointmentBeingDoctor() {
        //given
        String doctorId = "id";
        String appointmentId = appointmentCommandService.scheduleAppointment(new AppointmentFormDto(doctorId, "p", LocalDateTime.now()));
        //when
        appointmentCommandService.cancelAppointment(new CancelAppointmentApplication(appointmentId, Applicant.DOCTOR, doctorId));
        //then
        Assertions.assertTrue(appointmentQueryRepository.readAppointment(appointmentId).isEmpty());
    }


    @Test
    void shouldRescheduleAppointmentBeingDoctor() {
        //given
        String doctorId = "id";
        String appointmentId = appointmentCommandService.scheduleAppointment(new AppointmentFormDto(doctorId, "p", LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //when
        AppointmentDto appointmentDto = appointmentCommandService.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.DOCTOR, doctorId, newTime));
        //then
        Assertions.assertEquals(newTime, appointmentDto.getAppointmentTime());
    }


    @Test
    void shouldRescheduleAppointmentBeingPatient() {
        //given
        String patientId = "id";
        String appointmentId = appointmentCommandService.scheduleAppointment(new AppointmentFormDto("doc", patientId, LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //when
        AppointmentDto appointmentDto = appointmentCommandService.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.PATIENT, patientId, newTime));
        //then
        Assertions.assertEquals(newTime, appointmentDto.getAppointmentTime());
    }


}