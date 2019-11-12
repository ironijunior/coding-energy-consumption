package com.ironijunior.energyconsumption.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractIntegrationTest {

    protected static final String NEW_COUNTER_API = "/counter";
    protected static final String COUNTER_INFO_API = "/counter?id=%s";
    protected static final String REPORT_API = "/consumption_report?duration=%s";
    protected static final String NEW_CONSUMPTION_API = "/counter_callback";

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper mapper = new ObjectMapper();

}
