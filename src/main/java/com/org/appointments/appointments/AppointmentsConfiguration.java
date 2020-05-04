package com.org.appointments.appointments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppointmentsConfiguration {

    private final InMemoryRepository inmemoryRepository = new InMemoryRepository();


    AppointmentQueryRepository appointmentQueryRepository() {
        return appointmentQueryRepository(inmemoryRepository);
    }


    AppointmentCommandService appointmentCommandService() {
        return appointmentCommandService(inmemoryRepository);
    }

    @Bean
    AppointmentQueryRepository appointmentQueryRepository(AppointmentQueryRepository appointmentQueryRepository) {
        return appointmentQueryRepository;
    }


    @Bean
    AppointmentCommandService appointmentCommandService(AppointmentsRepository appointmentsRepository) {
        return new AppointmentCommandServiceImpl(appointmentsRepository);
    }

}
