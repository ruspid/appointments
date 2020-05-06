package com.org.appointments.appointments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppointmentsConfiguration {

    private final InMemoryRepository inmemoryRepository = new InMemoryRepository();
    private CabinetManager cabinetManager = new RandomCabinetManager();
    @Bean
    AppointmentQueryRepository appointmentQueryRepository() {
        return inmemoryRepository;
    }

    @Bean
    AppointmentFacade appointmentCommandService() {
        return new AppointmentFacadeImpl(inmemoryRepository, cabinetManager);
    }

}
