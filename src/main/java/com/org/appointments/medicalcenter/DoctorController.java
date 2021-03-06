package com.org.appointments.medicalcenter;


import com.org.appointments.medicalcenter.dto.DoctorDto;
import com.org.appointments.medicalcenter.dto.DoctorRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
class DoctorController {

    private final DoctorFacade doctorFacade;

    @GetMapping("/doctors/{id}")
    ResponseEntity<DoctorDto> readDoctor(@PathVariable String id) {
        return ResponseEntity.ok(doctorFacade.getDoctor(id));
    }

    @PostMapping("/doctors")
    ResponseEntity<Void> addDoctor(@RequestBody DoctorRegistrationDto dto) {
        return ResponseEntity.created(URI.create("/doctors/".concat(doctorFacade.registerDoctor(dto)))).build();
    }

    @DeleteMapping("/doctors/{id}")
    ResponseEntity<Void> removeDoctor(@PathVariable String id) {
        doctorFacade.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doctors")
    ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto dto) {
        return ResponseEntity.ok(doctorFacade.updateDoctor(dto));
    }


}
