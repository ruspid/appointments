package com.org.appointments.medicalcenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MedicalCenterConfiguration {
    @Bean
    DoctorFacade doctorFacade() {
        return new DoctorService(new InMemoryRepository());
    }
}
