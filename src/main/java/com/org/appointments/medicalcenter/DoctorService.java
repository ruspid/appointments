package com.org.appointments.medicalcenter;

import com.org.appointments.medicalcenter.dto.DoctorDto;
import com.org.appointments.medicalcenter.dto.DoctorRegistrationDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
public class DoctorService implements DoctorFacade {

    private final DoctorRepository doctorRepository;

    @Override
    public String registerDoctor(DoctorRegistrationDto dto) {
        return doctorRepository.register(DoctorCreator.from(dto)).getId();
    }

    @Override
    public DoctorDto getDoctor(String id) {
        return doctorRepository.findById(id)
                .map(Doctor::dto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.unregister(id);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto dto) {
        return doctorRepository.updateDoctorRecord(DoctorCreator.from(dto)).dto();
    }
}
