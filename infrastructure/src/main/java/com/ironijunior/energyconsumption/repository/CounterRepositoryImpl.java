package com.ironijunior.energyconsumption.repository;

import com.ironijunior.energyconsumption.Converter;
import com.ironijunior.energyconsumption.entity.CounterEntity;
import com.ironijunior.energyconsumption.exception.EntityNotFoundException;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.CounterRepositoryPort;
import com.ironijunior.energyconsumption.repository.spring.CounterSpringRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Adapter that implements the secondary port {@link CounterRepositoryPort}.
 * Responsible for the operations of {@link CounterEntity}
 *
 * @author Ironi Junior Medina
 */
public class CounterRepositoryImpl implements CounterRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(CounterRepositoryImpl.class);

    private CounterSpringRepository counterSpringRepository;

    public CounterRepositoryImpl(CounterSpringRepository counterSpringRepository) {
        this.counterSpringRepository = counterSpringRepository;
    }

    @Override
    public Counter findById(String id) {
        logger.info("Getting the counter for id {}", id);
        Optional<CounterEntity> counterEntity = counterSpringRepository.findById(id);

        return Converter.convertEntityToCounter(counterEntity
                .orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public Boolean save(Counter counter) {
        logger.info("Saving a new counter");
        Optional<CounterEntity> existentCounter = counterSpringRepository.findById(counter.getId());
        if (existentCounter.isEmpty()) {
            counterSpringRepository.save(Converter.convertCounterToEntity(counter));
        }
        return true;
    }

}
