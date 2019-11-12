package com.ironijunior.energyconsumption;

import com.ironijunior.energyconsumption.entity.CounterEntity;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.repository.CounterRepositoryImpl;
import com.ironijunior.energyconsumption.repository.spring.CounterSpringRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class CounterRepositoryImplTest {

    private CounterSpringRepository counterSpring = Mockito.mock(CounterSpringRepository.class);
    private CounterRepositoryImpl repository;

    @Before
    public void setup() {
        repository = new CounterRepositoryImpl(counterSpring);
    }

    @Test
    public void testFindById() {
        CounterEntity counter = new CounterEntity();
        counter.setId("1");
        counter.setName("counter1");

        Mockito.when(counterSpring.findById(anyString()))
                .thenReturn(Optional.of(counter));

        Counter expected = Converter.convertEntityToCounter(counter);
        Counter actual = repository.findById("1");
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
