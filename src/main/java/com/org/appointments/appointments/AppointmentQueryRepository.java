package com.org.appointments.appointments;


import com.org.appointments.appointments.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppointmentQueryRepository {
    Page<AppointmentDto> patientAppointments(String id, Pageable pageable);

    Page<AppointmentDto> scheduledAppointments(Pageable pageable);

    Optional<Appointment> readAppointment(String id);


}


/*
Możesz mieć z tyłu głowy iż w przyszłości chcielibyśmy w tej domenie wspierać grafiki pracy
(w jakich godzinach lekarz przyjmuje)  i
 jak domyślnie sloty wizyt się rozkładają by np. w aplikacji pacjenta można było umawiać wizytę).

 Wsparcie dla pakietów wizyt i wydarzeń cyklicznych – np. rehabilitacja trwająca 10 wizyt.
Te dwa ostatnie punkty daję bardziej w celu pokazania iż potencjalnie ta domena się będzie rozrastać i nadać kierunek gdzie może się rozrastać.
 */