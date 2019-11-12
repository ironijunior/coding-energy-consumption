package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.ConsumptionRequestDTO;
import com.ironijunior.energyconsumption.dto.ConsumptionResponseDTO;
import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.ConsumptionServicePort;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConsumptionControllerTest {

    private ConsumptionServicePort service = Mockito.mock(ConsumptionServicePort.class);
    private ConsumptionController controller;

    @Before
    public void setup() {
        controller = new ConsumptionController(service);
    }

    @Test
    public void saveConsumptionTest() {
        Mockito.when(service.sendCounterCallback(Mockito.anyString(), Mockito.any()))
                .thenReturn(Boolean.TRUE);

        ConsumptionRequestDTO request = ConsumptionRequestDTO.builder()
                .amount(BigDecimal.TEN)
                .counterId("1")
                .build();

        assertEquals(HttpStatus.CREATED, controller.saveConsumption(request).getStatusCode());
    }

    @Test
    public void getConsumptionReportTest() {
        List<Consumption> consumptions = getConsumptionsListMock();
        Mockito.when(service.obtainConsumptionReport(Mockito.anyString()))
                .thenReturn(consumptions);

        ResponseEntity<ConsumptionResponseDTO> response = controller.getConsumptionReport("24h");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getVillages()));
        assertEquals(1, response.getBody().getVillages().size());
    }

    private List<Consumption> getConsumptionsListMock() {
        Counter counter = new Counter();
        counter.setName("counter");
        counter.setName("1");

        Consumption cons = new Consumption();
        cons.setCounter(counter);
        cons.setConsumption(BigDecimal.TEN);

        return Arrays.asList(cons);
    }
}
