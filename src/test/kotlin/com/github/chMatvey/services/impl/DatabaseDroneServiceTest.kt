package com.github.chMatvey.services.impl

import com.github.chMatvey.dao.DroneDAOFacade
import com.github.chMatvey.models.Drone
import com.github.chMatvey.models.DroneModel.CRUISERWEIGHT
import com.github.chMatvey.models.DroneState.IDLE
import com.github.chMatvey.services.dto.request.DroneRegisterRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseDroneServiceTest {
    val droneDAOFacade: DroneDAOFacade = mockk<DroneDAOFacade>()
    val droneService = DatabaseDroneService(droneDAOFacade)

    @Test
    fun registerDrone(): Unit = runBlocking {
        // Given
        val registerRequest = DroneRegisterRequest(
            serialNumber = "DRN98711",
            model = CRUISERWEIGHT,
            weightLimit = 400
        )
        coEvery { droneDAOFacade.createDrone(any()) } returns (Drone(
            id = 1,
            serialNumber = registerRequest.serialNumber,
            model = registerRequest.model,
            weightLimit = registerRequest.weightLimit,
            batteryCapacity = 100,
            state = IDLE
        ))

        // When
        val registerResponse = droneService.registerDrone(registerRequest)

        // Then
        assertEquals(1, registerResponse.id)
        coVerify {
            droneDAOFacade.createDrone(
                Drone(
                    serialNumber = registerRequest.serialNumber,
                    model = registerRequest.model,
                    weightLimit = registerRequest.weightLimit,
                    batteryCapacity = 100,
                    state = IDLE
                )
            )
        }
    }
}