package com.org.appointments.patientregistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PatientConfiguration {

    private final InMemoryRepository inMemoryRepository = new InMemoryRepository();

    PatientFacade patientService() {
        return patientService(inMemoryRepository);
    }

    @Bean
    PatientFacade patientService(PatientRepository patientRepository) {
        return new PatientService(patientRepository);
    }

    @Bean
    PatientQueryRepository patientQueryRepository() {
        return inMemoryRepository;
    }
}
