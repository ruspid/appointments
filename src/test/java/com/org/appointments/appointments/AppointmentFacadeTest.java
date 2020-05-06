package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDateTime;

class AppointmentFacadeTest {

    private AppointmentFacade appointmentFacade;
    private AppointmentQueryRepository appointmentQueryRepository;

    @BeforeEach
    void init() {
        AppointmentsConfiguration configuration = new AppointmentsConfiguration();
        this.appointmentFacade = configuration.appointmentCommandService();
        this.appointmentQueryRepository = configuration.appointmentQueryRepository();
    }

    @Test
    void shouldScheduleAppointment() {
        //given
        AppointmentFormDto dto = new AppointmentFormDto("doctor", "patient", LocalDateTime.now());
        //when
        String appointmentId = appointmentFacade.scheduleAppointment(dto);
        //then
        Assertions.assertTrue(StringUtils.isNotBlank(appointmentId));
        //and
        AppointmentDto appointmentDto = appointmentFacade.readAppointment(appointmentId);
        Assertions.assertTrue(StringUtils.isNotBlank(appointmentDto.cabinetId));
    }

    @Test
    void shouldCancelAppointmentBeingPatient() {
        //given
        String patientId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto("doctor", patientId, LocalDateTime.now()));
        //when
        appointmentFacade.cancelAppointment(new CancelAppointmentApplication(appointmentId, Applicant.PATIENT, patientId));
        //then
        Assertions.assertTrue(appointmentQueryRepository.readAppointment(appointmentId).isEmpty());
    }


    @Test
    void shouldCancelAppointmentBeingDoctor() {
        //given
        String doctorId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto(doctorId, "p", LocalDateTime.now()));
        //when
        appointmentFacade.cancelAppointment(new CancelAppointmentApplication(appointmentId, Applicant.DOCTOR, doctorId));
        //then
        Assertions.assertTrue(appointmentQueryRepository.readAppointment(appointmentId).isEmpty());
    }


    @Test
    void shouldRescheduleAppointmentBeingDoctor() {
        //given
        String doctorId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto(doctorId, "p", LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //when
        AppointmentDto appointmentDto = appointmentFacade.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.DOCTOR, doctorId, newTime));
        //then
        Assertions.assertEquals(newTime, appointmentDto.getAppointmentTime());
    }


    @Test
    void shouldRescheduleAppointmentBeingPatient() {
        //given
        String patientId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto("doc", patientId, LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //when
        AppointmentDto appointmentDto = appointmentFacade.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.PATIENT, patientId, newTime));
        //then
        Assertions.assertEquals(newTime, appointmentDto.getAppointmentTime());
    }

    @Test
    void shouldNotAllowReschedulingForOtherPatients() {
        //given
        String patientId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto("doc", "differentId", LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //then
        Assertions.assertThrows(AppointmentAccessException.class,
                () -> appointmentFacade.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.PATIENT, patientId, newTime)));
    }


    @Test
    void shouldNotAllowReschedulingByAnotherDoctor() {
        //given
        String doctorId = "id";
        String appointmentId = appointmentFacade.scheduleAppointment(new AppointmentFormDto("differentId", "patient", LocalDateTime.now()));
        LocalDateTime newTime = LocalDateTime.now();
        //then
        Assertions.assertThrows(AppointmentAccessException.class,
                () -> appointmentFacade.rescheduleAppointment(new RescheduleAppointmentApplication(appointmentId, Applicant.DOCTOR, doctorId, newTime)));
    }

}