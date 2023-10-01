package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.BaseControllerTest;
import com.github.chMatvey.dronedelivery.model.Medication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.chMatvey.dronedelivery.TestUtil.readFileAsBytes;
import static com.github.chMatvey.dronedelivery.TestUtil.readFileAsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class MedicationControllerImplTest extends BaseControllerTest {

    @DisplayName("Should successfully created medication")
    @Test
    void createMedication() throws Exception {
        mockMvc.perform(post("/api/v1/medications")
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/medication/request/medication-creation-request.json")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @DisplayName("Should cannot create medication because name field is incorrect")
    @Test
    void createMedicationValidationFailed() throws Exception {
        mockMvc.perform(post("/api/v1/medications")
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/medication/request/medication-creation-incorrect-request.json")))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Should successfully update medication")
    @Test
    void partialUpdateMedication() throws Exception {
        int medicationId = 1001;

        mockMvc.perform(patch("/api/v1/medications/{medicationId}", medicationId)
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/medication/request/medication-partial-update-request.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(readFileAsString("json/medication/response/medication-partial-update-response.json")));

        Medication medication = entityManager
                .createQuery("select m from Medication m where m.id = :medicationId", Medication.class)
                .setParameter("medicationId", medicationId)
                .getSingleResult();

        assertEquals(90, medication.getWeight());
        assertEquals("JKL012", medication.getCode());
        assertEquals("Zoloft", medication.getName());
    }

    @DisplayName("Should cannot update medication because it not exist")
    @Test
    void partialUpdateMedicationNotFound() throws Exception {
        int notExistedMedicationId = 2001;

        mockMvc.perform(patch("/api/v1/medications/{medicationId}", notExistedMedicationId)
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/medication/request/medication-partial-update-request.json")))
                .andExpect(status().isNotFound());
    }
}