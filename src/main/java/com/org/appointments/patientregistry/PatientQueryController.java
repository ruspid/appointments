package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PatientQueryController {

    private final PatientQueryRepository patientQueryRepository;

    private Pageable getPageable(Integer perPage, Integer pageNum) {
        int page = pageNum != null ? pageNum : 1;
        int size = perPage != null ? perPage : 20;
        return PageRequest.of(page, size);
    }

    @GetMapping("/patients")
    ResponseEntity<Page<PatientDto>> readAllPatients(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", required = false) Integer perPage,
            @RequestParam(value = "size", required = false) Integer pageNum) {
        Pageable pageable = getPageable(perPage, pageNum);
        Page<PatientDto> patients = q == null ?
                patientQueryRepository.readAllPatients(pageable)
                : patientQueryRepository.searchPatients(q, pageable);
        return ResponseEntity.ok(patients);
    }


}
