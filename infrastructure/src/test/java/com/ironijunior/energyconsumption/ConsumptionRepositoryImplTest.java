package com.ironijunior.energyconsumption;

import com.ironijunior.energyconsumption.entity.ConsumptionEntity;
import com.ironijunior.energyconsumption.entity.CounterEntity;
import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.repository.ConsumptionRepositoryImpl;
import com.ironijunior.energyconsumption.repository.spring.ConsumptionSpringRepository;
import com.ironijunior.energyconsumption.repository.spring.CounterSpringRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ConsumptionRepositoryImplTest {

    private ConsumptionSpringRepository consumptionSpring = Mockito.mock(ConsumptionSpringRepository.class);
    private CounterSpringRepository counterSpring = Mockito.mock(CounterSpringRepository.class);
    private ConsumptionRepositoryImpl repository;

    @Before
    public void setup() {
        repository = new ConsumptionRepositoryImpl(consumptionSpring, counterSpring);
    }

    @Test
    public void testSavingConsumption() {
        Counter counterOne = new Counter();
        counterOne.setName("name");
        counterOne.setId("1");

        Mockito.when(counterSpring.findById(anyString()))
                .thenReturn(Optional.of(Converter.convertCounterToEntity(counterOne)));
        Mockito.when(consumptionSpring.save(any()))
                .thenReturn(new ConsumptionEntity());

        assertNotNull(repository.save(counterOne, new BigDecimal("200")));
    }

    @Test
    public void testSavingConsumptionAndCounter() {
        Counter counterOne = new Counter();
        counterOne.setName("name");
        counterOne.setId("1");

        Mockito.when(counterSpring.findById(anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(counterSpring.save(any()))
                .thenReturn(Converter.convertCounterToEntity(counterOne));
        Mockito.when(consumptionSpring.save(any()))
                .thenReturn(new ConsumptionEntity());

        assertNotNull(repository.save(counterOne, new BigDecimal("200")));
    }

    @Test
    public void testGettingConsumptions() {
        Mockito.when(consumptionSpring.findAllInLastDay(any(), any()))
                .thenReturn( getConsumptionsMocked());
        List<Consumption> consumptions = repository.findAllByDuration("24h");
        consumptions.forEach(c -> {
                if ("1".equals(c.getCounter().getId())) {
                    assertEquals(new BigDecimal("400"), c.getConsumption());
                } else {
                    assertEquals(new BigDecimal("300"), c.getConsumption());
                }
        });
    }

    private List<ConsumptionEntity> getConsumptionsMocked() {
        CounterEntity counter1 = new CounterEntity();
        counter1.setId("1");
        counter1.setName("counter1");
        CounterEntity counter2 = new CounterEntity();
        counter2.setId("2");
        counter2.setName("counter2");

        ConsumptionEntity c1 = new ConsumptionEntity();
        c1.setAmount(new BigDecimal("100"));
        c1.setCounter(counter1);
        c1.setCreatedAt(LocalDateTime.now().minusHours(2));
        ConsumptionEntity c2 = new ConsumptionEntity();
        c2.setAmount(new BigDecimal("300"));
        c2.setCounter(counter1);
        c2.setCreatedAt(LocalDateTime.now().minusHours(3));

        ConsumptionEntity c3 = new ConsumptionEntity();
        c3.setAmount(new BigDecimal("100"));
        c3.setCounter(counter2);
        c3.setCreatedAt(LocalDateTime.now().minusHours(48));

        ConsumptionEntity c4 = new ConsumptionEntity();
        c4.setAmount(new BigDecimal("200"));
        c4.setCounter(counter2);
        c4.setCreatedAt(LocalDateTime.now().minusHours(1));

        return Arrays.asList(c1, c2, c3, c4);
    }
}
