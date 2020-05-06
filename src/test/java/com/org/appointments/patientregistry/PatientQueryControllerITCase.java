package com.org.appointments.patientregistry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientQueryControllerITCase {

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnPageOffAllPatients() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/patients"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

}