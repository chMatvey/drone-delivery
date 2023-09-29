package com.github.chMatvey.dronedelivery.service;

import com.github.chMatvey.dronedelivery.web.request.DroneRegistrationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequest;
import com.github.chMatvey.dronedelivery.web.response.*;

public interface DroneService {
    DroneRegistrationResponse registerDrone(DroneRegistrationRequest request);

    MedicationsLoadResponse loadMedicationItems(int droneId, MedicationsLoadRequest request);

    MedicationsLoadedResponse getLoadedMedication(int droneId);

    AvailableDronesResponse getAvailableDrones();

    DroneBatteryLevelResponse getDroneBatteryLevel(int droneId);
}
