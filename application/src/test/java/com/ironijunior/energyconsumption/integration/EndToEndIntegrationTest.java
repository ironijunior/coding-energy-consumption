package com.ironijunior.energyconsumption.integration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EndToEndIntegrationTest extends AbstractIntegrationTest {

    @Test
    @Order(1)
    void createCountersTest() throws Exception {
         mockMvc.perform(
                post(NEW_COUNTER_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCounter("1")))
                .andExpect(status().isCreated());
        mockMvc.perform(
                post(NEW_COUNTER_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCounter("2")))
                .andExpect(status().isCreated());
        mockMvc.perform(
                post(NEW_COUNTER_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCounter("3")))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void gettingCounterInformationTest() throws Exception {
        String expectedJson = "{\"id\": \"1\", \"village_name\": \"counter 1\"}";
        mockMvc.perform(
                get(String.format(COUNTER_INFO_API, "1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson, true))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void addingConsumptionsTest() throws Exception {
        mockMvc.perform(
                post(NEW_CONSUMPTION_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getConsumption("1", new BigDecimal("100.000"))))
                .andExpect(status().isCreated());
        mockMvc.perform(
                post(NEW_CONSUMPTION_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getConsumption("1", new BigDecimal("200.000"))))
                .andExpect(status().isCreated());
        mockMvc.perform(
                post(NEW_CONSUMPTION_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getConsumption("3", new BigDecimal("673.040"))))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    public void gettingConsumptionReportTest() throws Exception {
        String expectedJson = "{" +
                "\"villages\":[" +
                "{\"village_name\":\"counter 3\",\"consumption\":673.040}," +
                "{\"village_name\":\"counter 1\",\"consumption\":300.000}]}";
        mockMvc.perform(
                get(String.format(REPORT_API, "24h")))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson, true))
                .andExpect(status().isOk());
    }

    private String getCounter(String id) {
        return String.format("{\"id\":\"%s\", \"name\":\"counter %s\"}", id, id);
    }

    private String getConsumption(String id, BigDecimal value) {
        return String.format("{\"counter_id\":\"%s\", \"amount\":%s}", id, value.toPlainString());
    }

}
