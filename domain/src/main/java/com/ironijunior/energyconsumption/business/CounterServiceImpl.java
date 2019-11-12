package com.ironijunior.energyconsumption.business;

import com.ironijunior.energyconsumption.exception.EntityNotFoundException;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.CounterRepositoryPort;
import com.ironijunior.energyconsumption.ports.CounterServicePort;

import java.util.Objects;

/**
 * Adapter for the counter service port.
 * This adapter calls the secondary ports that should return the data.
 *
 * @author Ironi Junior Medina
 */
public class CounterServiceImpl implements CounterServicePort {

    private CounterRepositoryPort repository;

    /**
     * Constructor
     * @param repository counter port
     */
    public CounterServiceImpl(CounterRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Counter obtainCounterInformation(String counterId) {
        Counter counter  = repository.findById(counterId);
        if (Objects.isNull(counter)) {
            throw new EntityNotFoundException(counterId);
        }
        return counter;
    }

    @Override
    public Boolean saveCounter(Counter counter) {
        return repository.save(counter);
    }
}
