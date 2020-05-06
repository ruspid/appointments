package com.org.appointments.patientregistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PatientConfiguration {

    private final InMemoryRepository inMemoryRepository = new InMemoryRepository();

    @Bean
    PatientFacade patientService() {
        return new PatientService(inMemoryRepository);
    }

    @Bean
    PatientQueryRepository patientQueryRepository() {
        return inMemoryRepository;
    }
}
