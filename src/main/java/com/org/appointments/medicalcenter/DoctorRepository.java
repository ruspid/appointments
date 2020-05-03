package com.org.appointments.medicalcenter;

import java.util.Optional;

public interface DoctorRepository {
    Optional<Doctor> findById(String id);

    Doctor register(Doctor doctor);

    void unregister(String id);

    Doctor updateDoctorRecord(Doctor doctor);
}
