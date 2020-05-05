package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
class PatientQueryController {

    private final PatientQueryRepository patientQueryRepository;

    @GetMapping("/patients")
    ResponseEntity<Page<PatientDto>> readAllPatients(@RequestParam(value = "q", required = false) Optional<String> q, Pageable pageable) {
        Page<PatientDto> patients = q.map(p -> patientQueryRepository.searchPatients(p, pageable))
                .orElseGet(() -> patientQueryRepository.readAllPatients(pageable));
        return ResponseEntity.ok(patients);
    }



}
