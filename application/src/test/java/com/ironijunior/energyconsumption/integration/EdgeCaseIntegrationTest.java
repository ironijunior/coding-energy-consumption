package com.ironijunior.energyconsumption.integration;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EdgeCaseIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void gettingNonExistentCounterTest() throws Exception {
        mockMvc.perform(
                get(String.format(COUNTER_INFO_API, "xxx")))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":404,\"message\":\"There is no data with the identifier xxx.\"}", true));
    }

    @Test
    public void gettingNotFoundOnInvalidUrl() throws Exception {
        mockMvc.perform(
                get("/non-existent/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void gettingMethodNotAllowedTest() throws Exception {
        mockMvc.perform(
                get(NEW_CONSUMPTION_API))
                .andExpect(status().isMethodNotAllowed());
    }
}
