package com.org.appointments.appointments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppointmentsConfiguration {

    private final InMemoryRepository inmemoryRepository = new InMemoryRepository();

    @Bean
    AppointmentQueryRepository appointmentQueryRepository() {
        return inmemoryRepository;
    }

    @Bean
    AppointmentCommandService appointmentCommandService() {
        return new AppointmentCommandServiceImpl(inmemoryRepository);
    }

}
