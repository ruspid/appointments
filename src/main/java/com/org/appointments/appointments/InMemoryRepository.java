package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

class InMemoryRepository implements AppointmentsRepository, AppointmentQueryRepository {

    private final Comparator<String> idComparator = Comparator.comparing(String::toString);
    private Map<String, Appointment> appointments = Collections.synchronizedSortedMap(new TreeMap<>(idComparator));


    @Override
    public String addAppointment(Appointment appointment) {
        appointment.setAppointmentId(generateId());
        appointments.put(appointment.getAppointmentId(), appointment);
        return appointment.getAppointmentId();
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        appointments.put(appointment.getAppointmentId(), appointment);
        return appointment;
    }

    @Override
    public void cancelAppointment(String id) {
        appointments.remove(id);
    }

    @Override
    public Optional<Appointment> readAppointment(String id) {
        return Optional.ofNullable(appointments.get(id));
    }

    @Override
    public Page<AppointmentDto> scheduledAppointments(Pageable pageable) {
        List<AppointmentDto> appointmentsPage = appointments.values()
                .stream()
                .map(Appointment::dto)
                .skip(numberOfRecordsToSkip(pageable))
                .limit(pageable.getOffset())
                .collect(Collectors.toList());
        return new PageImpl<>(appointmentsPage, pageable, appointments.size());
    }

    @Override
    public Page<AppointmentDto> patientAppointments(String id, Pageable pageable) {
        List<AppointmentDto> appointmentsPage = appointments.values()
                .stream()
                .filter(a -> patientAppointmentPredicate(a, id))
                .map(Appointment::dto)
                .skip(numberOfRecordsToSkip(pageable))
                .limit(pageable.getOffset())
                .collect(Collectors.toList());
        return new PageImpl<>(appointmentsPage, pageable, appointments.size());
    }

    private boolean patientAppointmentPredicate(Appointment appointment, String id) {
        return appointment.getPatientId().equals(id);
    }

    private long numberOfRecordsToSkip(Pageable pageable) {
        return (pageable.getPageNumber()-1) * pageable.getPageSize();
    }

    private String generateId() {
        String id;
        do
            id = UUID.randomUUID().toString();
        while
        (appointments.containsKey(id));
        return id;
    }

}
