package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class PatientQueryController {

    private final PatientQueryRepository patientQueryRepository;

    @GetMapping("/patients")
    ResponseEntity<Page<PatientDto>> readAllPatients(Pageable pageable) {
        return ResponseEntity.ok(patientQueryRepository.readAllPatients(pageable));
    }


}
