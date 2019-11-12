package com.ironijunior.energyconsumption.ports;

import com.ironijunior.energyconsumption.model.Consumption;

import java.math.BigDecimal;
import java.util.List;

/**
 * External port for Consumption
 * Every external access should be done by this port.
 *
 * @author Ironi Junior Medina
 */
public interface ConsumptionServicePort {

    /**
     * Set an consumption amount to the specified counter id
     * @param counterId Counter identification
     * @param amount Amount
     * @return {@code true} if success or {@code false} if not.
     */
    Boolean sendCounterCallback(String counterId, BigDecimal amount);

    /**
     * Obtain the consumption report
     * @param duration Duration of the report (24 hrs)
     * @return List of consumption for the informed duration
     */
    List<Consumption> obtainConsumptionReport(String duration);
}
