package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientQueryControllerITCase {

    @Autowired
    MockMvc mvc;

    @Autowired
    PatientFacade patientFacade;

    @BeforeAll
    public void initTestData() {
        patientFacade.registerPatient(new PatientRegistrationDto("John", "Wick", "NY"));
        patientFacade.registerPatient(new PatientRegistrationDto("Wiz", "Cudi", "LA"));
        patientFacade.registerPatient(new PatientRegistrationDto("Joyner", "Lucas", "AT"));
    }

    @Test
    void shouldReturnPageOffAllPatients() throws Exception {
        //given - testData
        //when
        mvc.perform(MockMvcRequestBuilders.get("/patients"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    void shouldReturnPageOfMatchingPatients() throws Exception {
        //given - testData
        //when
        mvc.perform(MockMvcRequestBuilders.get("/patients").param("q", "jo"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }


}