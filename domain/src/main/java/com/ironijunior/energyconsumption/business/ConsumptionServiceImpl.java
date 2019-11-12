package com.ironijunior.energyconsumption.business;

import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.ConsumptionRepositoryPort;
import com.ironijunior.energyconsumption.ports.ConsumptionServicePort;
import com.ironijunior.energyconsumption.ports.CounterServicePort;

import java.math.BigDecimal;
import java.util.List;

/**
 * Adapter for the consumption service port.
 * This adapter calls the secondary ports that should return the data.
 *
 * @author Ironi Junior Medina
 */
public class ConsumptionServiceImpl implements ConsumptionServicePort {

    private ConsumptionRepositoryPort consumptionRepository;
    private CounterServicePort counterService;

    /**
     * Constructor
     * @param consumptionRepository consumption port
     * @param counterService counter service port
     */
    public ConsumptionServiceImpl(ConsumptionRepositoryPort consumptionRepository,
            CounterServicePort counterService) {
        this.consumptionRepository = consumptionRepository;
        this.counterService = counterService;
    }

    @Override
    public Boolean sendCounterCallback(String counterId, BigDecimal amount) {
        Counter counter = counterService.obtainCounterInformation(counterId);
        return consumptionRepository.save(counter, amount);
    }

    @Override
    public List<Consumption> obtainConsumptionReport(String duration) {
        return consumptionRepository.findAllByDuration(duration);
    }
}
