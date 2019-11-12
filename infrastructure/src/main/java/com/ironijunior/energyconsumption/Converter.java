package com.ironijunior.energyconsumption;

import com.ironijunior.energyconsumption.entity.ConsumptionEntity;
import com.ironijunior.energyconsumption.entity.CounterEntity;
import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.model.Counter;

/**
 * Class with helper methods to convert entities in domain models
 * and vice versa.
 *
 * @author Ironi Junior Medina
 */
public final class Converter {

    /**
     * Converts a {@link Counter} object into {@link CounterEntity}
     * @param counter {@link Counter} object
     * @return {@link CounterEntity} object
     */
    public static CounterEntity convertCounterToEntity(Counter counter) {
        CounterEntity entity = new CounterEntity();
        entity.setId(counter.getId());
        entity.setName(counter.getName());
        return entity;
    }

    /**
     * Converts a {@link Consumption} object into {@link ConsumptionEntity}
     * @param consumption {@link Consumption} object
     * @return {@link ConsumptionEntity} object
     */
    public static ConsumptionEntity convertConsumptionToEntity(Consumption consumption) {
        ConsumptionEntity entity = new ConsumptionEntity();
        entity.setCounter(convertCounterToEntity(consumption.getCounter()));
        entity.setAmount(consumption.getConsumption());
        return entity;
    }

    /**
     * Converts a {@link CounterEntity} object into {@link Counter}
     * @param entity {@link CounterEntity} object
     * @return {@link Counter} object
     */
    public static Counter convertEntityToCounter(CounterEntity entity) {
        Counter counter = new Counter();
        counter.setId(entity.getId());
        counter.setName(entity.getName());
        return counter;
    }

    /**
     * Converts a {@link ConsumptionEntity} object into {@link Consumption}
     * @param entity {@link ConsumptionEntity} object
     * @return {@link Consumption} object
     */
    public static Consumption convertEntityToConsumption(ConsumptionEntity entity) {
        Consumption consumption = new Consumption();
        consumption.setCounter(convertEntityToCounter(entity.getCounter()));
        consumption.setConsumption(entity.getAmount());
        return consumption;
    }

}
