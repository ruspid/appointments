package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
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
class AppointmentQueryController {


    private final AppointmentQueryRepository repository;

    @GetMapping(value = {"/appointments"})
    ResponseEntity<Page<AppointmentDto>> readAppointment(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "patient_id", required = false) String patientId) {
        Page<AppointmentDto> appointments = patientId != null ?
                repository.patientAppointments(patientId, pageable)
                : repository.scheduledAppointments(pageable);
        return ResponseEntity.ok(appointments);
    }


}
