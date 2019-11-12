package com.ironijunior.energyconsumption.repository.spring;

import com.ironijunior.energyconsumption.entity.CounterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is the responsible for the operations
 * on the {@link CounterEntity}
 *
 * @author Ironi Junior Medina
 */
@Repository
public interface CounterSpringRepository extends CrudRepository<CounterEntity, String> {
}
