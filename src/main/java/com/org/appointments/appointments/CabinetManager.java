package com.org.appointments.appointments;

import java.time.LocalDateTime;

interface CabinetManager {

    String findCabinetForAppointment(LocalDateTime appointmentTime, String doctorId);
}
