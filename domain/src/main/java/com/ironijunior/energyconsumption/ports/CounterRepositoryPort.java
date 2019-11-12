package com.ironijunior.energyconsumption.ports;

import com.ironijunior.energyconsumption.model.Counter;

/**
 * External port
 *
 * @author Ironi Junior Medina
 */
public interface CounterRepositoryPort {

    /**
     * Obtain a counter by its id.
     * @param id Counter identification
     * @return a Counter
     */
    Counter findById(String id);

    /**
     * Saves a counter register
     * @param counter Counter
     * @return {@code true} if success or {@code false} if not
     */
    Boolean save(Counter counter);
}
