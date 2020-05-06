package com.org.appointments.appointments;

import java.time.LocalDateTime;
import java.util.UUID;

public class RandomCabinetManager implements CabinetManager {
    @Override
    public String findCabinetForAppointment(LocalDateTime appointmentTime, String doctorId) {
        return UUID.randomUUID().toString();
    }
}
