package com.github.chMatvey.dronedelivery;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class BaseControllerTest extends PostgresContainerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected EntityManager entityManager;
}
