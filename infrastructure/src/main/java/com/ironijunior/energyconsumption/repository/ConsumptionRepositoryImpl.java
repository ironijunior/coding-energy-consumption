package com.ironijunior.energyconsumption.repository;

import com.ironijunior.energyconsumption.Converter;
import com.ironijunior.energyconsumption.entity.ConsumptionEntity;
import com.ironijunior.energyconsumption.entity.CounterEntity;
import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.ConsumptionRepositoryPort;
import com.ironijunior.energyconsumption.repository.spring.ConsumptionSpringRepository;
import com.ironijunior.energyconsumption.repository.spring.CounterSpringRepository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Adapter that implements the secondary port {@link ConsumptionRepositoryPort}.
 * Responsible for the operations of {@link ConsumptionEntity}
 *
 * @author Ironi Junior Medina
 */
public class ConsumptionRepositoryImpl implements ConsumptionRepositoryPort {

    private static final long TWENTY_FOUR_HOURS = 24;

    private ConsumptionSpringRepository consumptionSpringRepository;
    private CounterSpringRepository counterSpringRepository;

    public ConsumptionRepositoryImpl(
            ConsumptionSpringRepository consumptionSpringRepository, CounterSpringRepository counterSpringRepository) {
        this.consumptionSpringRepository = consumptionSpringRepository;
        this.counterSpringRepository = counterSpringRepository;
    }

    @Override
    public List<Consumption> findAllByDuration(String duration) {
        LocalDateTime finalDate = LocalDateTime.now();
        LocalDateTime initialDate = finalDate.minusWeeks(1);
        if ("24h".equalsIgnoreCase(duration)) {
            initialDate = finalDate.minusHours(TWENTY_FOUR_HOURS);
        }

        List<ConsumptionEntity> entities = consumptionSpringRepository.findAllInLastDay(initialDate, finalDate);
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return transformEntitiesToConsumption(entities);
    }

    @Override
    public Boolean save(Counter counter, BigDecimal amount) {
        Optional<CounterEntity> counterEntity = counterSpringRepository.findById(counter.getId());

        ConsumptionEntity consumptionEntity = new ConsumptionEntity();
        consumptionEntity.setCreatedAt(LocalDateTime.now());
        consumptionEntity.setAmount(amount);

        counterEntity.ifPresent(consumptionEntity::setCounter);

        return consumptionSpringRepository.save(consumptionEntity) != null;
    }

    private List<Consumption> transformEntitiesToConsumption(List<ConsumptionEntity> entities) {
        Map<CounterEntity, List<ConsumptionEntity>> consumptionsPerCounter = entities.stream()
                .collect(groupingBy(ConsumptionEntity::getCounter));

        return consumptionsPerCounter.entrySet().stream()
                .map(entry -> {
                    Consumption consumption = new Consumption();
                    consumption.setConsumption(
                            entry.getValue().stream()
                                    .map(ConsumptionEntity::getAmount)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    );
                    consumption.setCounter(Converter.convertEntityToCounter(entry.getKey()));
                    return consumption;
                })
                .collect(toList());
    }
}
