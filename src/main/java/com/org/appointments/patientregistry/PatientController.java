package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
class PatientController {

    private final PatientFacade patientFacade;

    @GetMapping("/patients/{id}")
    ResponseEntity<PatientDto> searchPatient(@PathVariable String id) {
        return ResponseEntity.ok(patientFacade.searchPatient(id));
    }

    @PostMapping("/patients")
    ResponseEntity<Void> registerPatient(@RequestBody PatientRegistrationDto dto) {
        return ResponseEntity.created(URI.create("/patient/".concat(patientFacade.registerPatient(dto)))).build();
    }

    @DeleteMapping("/patients/{id}")
    ResponseEntity<Void> deletePatientRecord(@PathVariable String id) {
        patientFacade.deletePatientRecord(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/patients")
    ResponseEntity<PatientDto> updatePatientRecord(@RequestBody PatientDto dto) {
        return ResponseEntity.ok(patientFacade.updatePatientRecord(dto));
    }


}
