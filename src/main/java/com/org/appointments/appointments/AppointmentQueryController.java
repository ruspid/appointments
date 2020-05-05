package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
class AppointmentQueryController {

    private final AppointmentQueryRepository repository;

    @GetMapping(value = "/{id}")
    ResponseEntity<Page<AppointmentDto>> readAppointment(@PathVariable(required = false) Optional<String> patientId, Pageable pageable){
        Page<AppointmentDto> appointments = patientId.map(id -> repository.patientAppointments(id, pageable))
                .orElseGet(() -> repository.scheduledAppointments(pageable));
        return ResponseEntity.ok(appointments);
    }



}
