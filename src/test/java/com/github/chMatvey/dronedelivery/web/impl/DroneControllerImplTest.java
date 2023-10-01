package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.BaseControllerTest;
import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.chMatvey.dronedelivery.TestUtil.readFileAsBytes;
import static com.github.chMatvey.dronedelivery.model.DroneModel.HEAVYWEIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DroneControllerImplTest extends BaseControllerTest {

    @DisplayName("Should successfully register drone")
    @Test
    void registerDrone() throws Exception {
        String contentAsString = mockMvc.perform(post("/api/v1/drones/register")
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/drone/request/drone-register-request.json")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn()
                .getResponse()
                .getContentAsString();

        int droneId = JsonPath.read(contentAsString, "$.id");

        Drone drone = entityManager.createQuery("select d from Drone d where d.id = :droneId", Drone.class)
                .setParameter("droneId", droneId)
                .getSingleResult();

        assertEquals("DRN98777", drone.getSerialNumber());
        assertEquals(HEAVYWEIGHT, drone.getModel());
        assertEquals(500, drone.getWeightLimit());
    }

    @DisplayName("Should load medications to drone successfully")
    @Test
    void loadMedicationsToDrone() throws Exception {
        int droneId = 2001;

        String contentAsString = mockMvc.perform(post("/api/v1/drones/{droneId}/medications", droneId)
                        .contentType(APPLICATION_JSON)
                        .content(readFileAsBytes("json/drone/request/drone-load-medications-request.json")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        int deliveryId = JsonPath.read(contentAsString, "$.deliveryId");

        Delivery delivery = entityManager.createQuery("select d from Delivery d join fetch d.deliveryItems where d.id = :deliveryId", Delivery.class)
                .setParameter("deliveryId", deliveryId)
                .getSingleResult();

        assertEquals(droneId, delivery.getDrone().getId());
        assertEquals(2, delivery.getDeliveryItems().size());
        assertEquals(3, delivery.getMedications().size());
    }
}