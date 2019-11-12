package com.ironijunior.energyconsumption.ports;

import com.ironijunior.energyconsumption.model.Counter;

/**
 * External port for Counter
 * Every external access should be done by this port.
 *
 * @author Ironi Junior Medina
 */
public interface CounterServicePort {

    /**
     * Obtain the counter information from the specified Id
     * @param counterId Counter identification
     * @return Counter information
     */
    Counter obtainCounterInformation(String counterId);

    /**
     * Write a new counter to the database;
     * @param counter {@link Counter} object
     * @return {@code true} if success and {@false} if not.
     */
    Boolean saveCounter(Counter counter);
}
