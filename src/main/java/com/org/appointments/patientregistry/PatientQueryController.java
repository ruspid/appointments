package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PatientQueryController {

    private final PatientQueryRepository patientQueryRepository;

    @GetMapping("/patients")
    ResponseEntity<Page<PatientDto>> readAllPatients(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault Pageable pageable) {
        Page<PatientDto> patients = q == null ?
                patientQueryRepository.readAllPatients(pageable)
                : patientQueryRepository.searchPatients(q, pageable);
        return ResponseEntity.ok(patients);
    }


}
