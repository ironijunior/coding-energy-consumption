package com.ironijunior.energyconsumption.repository.spring;

import com.ironijunior.energyconsumption.entity.ConsumptionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is the responsible for the operations
 * on the {@link ConsumptionEntity}
 *
 * @author Ironi Junior Medina
 */
@Repository
public interface ConsumptionSpringRepository extends CrudRepository<ConsumptionEntity, Long> {

    /**
     * Query to get all the consumptions in certain period of time.
     * @param initialDate Initial date.
     * @param finalDate Final Date
     * @return List of consumptions that matches the date filter.
     */
    @Query("SELECT c FROM ConsumptionEntity c WHERE c.createdAt >= ?1 AND c.createdAt <= ?2")
    List<ConsumptionEntity> findAllInLastDay(LocalDateTime initialDate, LocalDateTime finalDate);
}
