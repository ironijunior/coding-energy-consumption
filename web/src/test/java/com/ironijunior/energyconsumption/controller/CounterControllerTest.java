package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.CounterDTO;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.CounterServicePort;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class CounterControllerTest {

    private CounterServicePort service = Mockito.mock(CounterServicePort.class);
    private CounterController controller;

    @Before
    public void setup() {
        controller = new CounterController(service);
    }

    @Test
    public void getCounterTest() {
        Counter counter = new Counter();
        counter.setId("1");
        counter.setName("counter");

        Mockito.when(service.obtainCounterInformation(Mockito.anyString()))
                .thenReturn(counter);

        ResponseEntity<CounterDTO> response = controller.getCounter("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(counter.getId(), response.getBody().getId());
        assertEquals(counter.getName(), response.getBody().getVillageName());
    }
}
