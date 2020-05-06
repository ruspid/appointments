package com.org.appointments.appointments;

import com.org.appointments.appointments.dto.AppointmentFormDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppointmentQueryControllerITCase {

    @Autowired
    MockMvc mvc;

    @Autowired
    AppointmentCommandService commandService;

    @BeforeAll
    public void initTestData() {
        commandService.scheduleAppointment(new AppointmentFormDto("doctorId", "patientId", LocalDateTime.now()));
        commandService.scheduleAppointment(new AppointmentFormDto("doctorId", "1", LocalDateTime.now()));
        commandService.scheduleAppointment(new AppointmentFormDto("doctorId", "1", LocalDateTime.now()));
    }

    @Test
    void shouldReturnPageOfPatientAppointments() throws Exception {
        //given - testData

        //when
        mvc.perform(MockMvcRequestBuilders.get("/appointments")
                .param("patient_id", "1"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void shouldReturnPageOfAllScheduledAppointments() throws Exception {
        //given - testData
        //when
        mvc.perform(MockMvcRequestBuilders.get("/appointments"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }


}