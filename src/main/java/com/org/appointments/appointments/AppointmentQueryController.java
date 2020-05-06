package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AppointmentQueryController {


    private final AppointmentQueryRepository repository;

    @GetMapping(value = {"/appointments"})
    ResponseEntity<Page<AppointmentDto>> readAppointment(
            @RequestParam(value = "patient_id", required = false) String patientId,
            @RequestParam(value = "page", required = false) Integer perPage,
            @RequestParam(value = "size", required = false) Integer pageNum) {
        Pageable pageable = getPageable(perPage, pageNum);
        Page<AppointmentDto> appointments = patientId != null ?
                repository.patientAppointments(patientId, pageable)
                : repository.scheduledAppointments(pageable);
        return ResponseEntity.ok(appointments);
    }

    private Pageable getPageable(Integer perPage, Integer pageNum) {
        int page = pageNum != null ? pageNum : 1;
        int size = perPage != null ? perPage : 20;
        return PageRequest.of(page, size);
    }


}
