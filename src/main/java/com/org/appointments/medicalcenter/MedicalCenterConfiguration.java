package com.org.appointments.medicalcenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MedicalCenterConfiguration {

    DoctorFacade doctorFacade(){
        return doctorFacade(new InMemoryRepository());
    }
    @Bean
    DoctorFacade doctorFacade(DoctorRepository doctorRepository){
        return new DoctorService(doctorRepository);
    }
}
