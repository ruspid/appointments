package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentsRepository appointmentsRepository;

    @Override
    public String scheduleAppointment(AppointmentFormDto appointmentDto) {
        return appointmentsRepository.addAppointment(AppointmentCreator.from(appointmentDto));
    }

    @Override
    public AppointmentDto rescheduleAppointment(RescheduleAppointmentApplication dto) {
        return null;
    }

    @Override
    public void cancelAppointment(CancelAppointmentApplication application) {
        Appointment appointment = appointmentsRepository.readAppointment(application.appointmentId)
                .orElseThrow(EntityNotFoundException::new);
        if (!canCancelAppointment(appointment, application)) throw new RuntimeException();
        appointmentsRepository.cancelAppointment(appointment.getAppointmentId());
    }

    private boolean canCancelAppointment(Appointment appointment, CancelAppointmentApplication application) {
        if(application.applicant.equals(Applicant.PATIENT)) return isPatient(appointment, application);
        return isPartOfOrganization(appointment, application);
    }

    private boolean isPartOfOrganization(Appointment appointment, CancelAppointmentApplication application) {
        return appointment.getDoctorId().equals(application.getApplicantId());
    }

    private boolean isPatient(Appointment appointment, CancelAppointmentApplication application) {
        return appointment.getPatientId().equals(application.applicantId);
    }

}
