package com.ironijunior.energyconsumption.model;

import java.math.BigDecimal;

/**
 * Domain class that represent the Consumption information
 *
 * @author Ironi Junior Medina
 */
public class Consumption {

    private Counter counter;

    private BigDecimal consumption;

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }
}
