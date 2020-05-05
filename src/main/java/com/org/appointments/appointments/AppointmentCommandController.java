package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentDto;
import com.org.appointments.appointments.dto.AppointmentFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
class AppointmentCommandController {

    private final AppointmentCommandService service;

    @PostMapping(value = "/appointments")
    ResponseEntity<Void> scheduleAppointment(@RequestBody AppointmentFormDto dto) {
        return ResponseEntity.created(URI.create("/appointment/" + service.scheduleAppointment(dto))).build();
    }

    @PutMapping(value = "/appointments")
    ResponseEntity<AppointmentDto> rescheduleAppointment(@RequestBody RescheduleAppointmentApplication application) {
        return ResponseEntity.ok(service.rescheduleAppointment(application));
    }

    @DeleteMapping(value = "/appointments")
    ResponseEntity<Void> cancelAppointment(@RequestBody CancelAppointmentApplication application) {
        service.cancelAppointment(application);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/appointment/{id}")
    ResponseEntity<AppointmentDto> readAppointment(@PathVariable String id){
        return ResponseEntity.ok(service.readAppointment(id));
    }


}
