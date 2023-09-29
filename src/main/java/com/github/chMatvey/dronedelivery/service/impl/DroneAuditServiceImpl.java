package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.DroneAudit;
import com.github.chMatvey.dronedelivery.repository.DroneAuditRepository;
import com.github.chMatvey.dronedelivery.repository.DroneRepository;
import com.github.chMatvey.dronedelivery.service.DroneAuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneAuditServiceImpl implements DroneAuditService {
    private final DroneRepository droneRepository;
    private final DroneAuditRepository auditRepository;

    @Scheduled(cron = "${audit.drone.cron}")
    @Override
    public void createAuditEvents() {
        log.info("Started creating audit events");

        List<DroneAudit> auditList = droneRepository.findAll().stream()
                .map(Drone::createAudit)
                .toList();
        auditRepository.saveAll(auditList);

        log.info("Finished creating audit events");
    }
}
