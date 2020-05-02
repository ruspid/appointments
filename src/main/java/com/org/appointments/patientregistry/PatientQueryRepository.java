package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface PatientQueryRepository {

    Page<PatientDto> readAllPatients(Pageable pageable);
}
