package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppointmentFacadeImpl implements AppointmentFacade {

    private final AppointmentsRepository appointmentsRepository;

    @Override
    public String scheduleAppointment(AppointmentFormDto appointmentDto) {
        return appointmentsRepository.addAppointment(AppointmentCreator.from(appointmentDto));
    }

    @Override
    public AppointmentDto rescheduleAppointment(RescheduleAppointmentApplication application) {
        Appointment appointment = getAppointmentById(application.appointmentId);
        if (!canRescheduleAppointment(appointment, application)) throw new AppointmentAccessException();
        appointment.setAppointmentTime(application.getNewAppointmentTime());
        appointmentsRepository.addAppointment(appointment);
        return appointment.dto();
    }

    @Override
    public void cancelAppointment(CancelAppointmentApplication application) {
        Appointment appointment = getAppointmentById(application.appointmentId);
        if (!canCancelAppointment(appointment, application)) throw new AppointmentAccessException();
        appointmentsRepository.cancelAppointment(appointment.getAppointmentId());
    }

    @Override
    public AppointmentDto readAppointment(String id) {
        return getAppointmentById(id).dto();
    }

    private Appointment getAppointmentById(String id) {
        return appointmentsRepository.readAppointment(id)
                .orElseThrow(() -> new AppointmentNotFountException(id));
    }

    private boolean canCancelAppointment(Appointment appointment, CancelAppointmentApplication application) {
        if (application.applicant.equals(Applicant.PATIENT)) return isPatient(appointment, application);
        return isPartOfOrganization(appointment, application);
    }

    private boolean isPartOfOrganization(Appointment appointment, CancelAppointmentApplication application) {
        return appointment.getDoctorId().equals(application.getApplicantId());
    }

    private boolean isPatient(Appointment appointment, CancelAppointmentApplication application) {
        return appointment.getPatientId().equals(application.applicantId);
    }


    private boolean isPartOfOrganization(Appointment appointment, RescheduleAppointmentApplication application) {
        return appointment.getDoctorId().equals(application.getApplicantId());
    }

    private boolean isPatient(Appointment appointment, RescheduleAppointmentApplication application) {
        return appointment.getPatientId().equals(application.applicantId);
    }


    private boolean canRescheduleAppointment(Appointment appointment, RescheduleAppointmentApplication application) {
        if (application.applicant.equals(Applicant.PATIENT)) return isPatient(appointment, application);
        return isPartOfOrganization(appointment, application);
    }

}
