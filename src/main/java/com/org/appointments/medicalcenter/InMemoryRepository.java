package com.org.appointments.medicalcenter;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryRepository implements DoctorRepository {

    private ConcurrentHashMap<String, Doctor> doctorsRegister = new ConcurrentHashMap<>();

    @Override
    public Optional<Doctor> findById(String id) {
        return Optional.ofNullable(doctorsRegister.get(id));
    }

    @Override
    public Doctor register(Doctor doctor) {
        doctor.setId(generateId());
        doctorsRegister.put(doctor.getId(), doctor);
        return doctor;
    }

    @Override
    public void unregister(String id) {
        doctorsRegister.remove(id);
    }

    @Override
    public Doctor updateDoctorRecord(Doctor doctor) {
        return doctorsRegister.put(doctor.getId(), doctor);
    }

    private String generateId(){
        String id;
        do
            id = UUID.randomUUID().toString();
        while
            (doctorsRegister.containsKey(id));
        return id;
    }
}
