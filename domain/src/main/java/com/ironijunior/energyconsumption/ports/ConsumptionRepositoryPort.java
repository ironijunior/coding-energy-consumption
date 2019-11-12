package com.ironijunior.energyconsumption.ports;

import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;

import java.math.BigDecimal;
import java.util.List;

/**
 * External port to operations.
 *
 * @author Ironi Junior Medina
 */
public interface ConsumptionRepositoryPort {

    /**
     * Return all consumptions for the specified duration
     * @param duration Duration
     * @return list of consumptions
     */
    List<Consumption> findAllByDuration(String duration);

    /**
     * Saves a consumption register to the specified Counter
     * @param counter Counter
     * @param amount amount of consumption
     * @return {@code true} if success or {@code false} if not
     */
    Boolean save(Counter counter, BigDecimal amount);

}
