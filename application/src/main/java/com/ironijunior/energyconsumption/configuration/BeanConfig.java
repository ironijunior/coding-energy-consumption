package com.ironijunior.energyconsumption.configuration;

import com.ironijunior.energyconsumption.business.ConsumptionServiceImpl;
import com.ironijunior.energyconsumption.business.CounterServiceImpl;
import com.ironijunior.energyconsumption.ports.ConsumptionRepositoryPort;
import com.ironijunior.energyconsumption.ports.ConsumptionServicePort;
import com.ironijunior.energyconsumption.ports.CounterRepositoryPort;
import com.ironijunior.energyconsumption.ports.CounterServicePort;
import com.ironijunior.energyconsumption.repository.ConsumptionRepositoryImpl;
import com.ironijunior.energyconsumption.repository.CounterRepositoryImpl;
import com.ironijunior.energyconsumption.repository.spring.ConsumptionSpringRepository;
import com.ironijunior.energyconsumption.repository.spring.CounterSpringRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible for define the beans of this application
 *
 * @author Ironi Junior Medina
 */
@Configuration
public class BeanConfig {

    @Bean
    public CounterServicePort counterService(CounterRepositoryPort counterRepositoryPort) {
        return new CounterServiceImpl(counterRepositoryPort);
    }

    @Bean
    public ConsumptionServicePort consumptionService(
            ConsumptionRepositoryPort consumptionRepositoryPort, CounterServicePort counterService) {
        return new ConsumptionServiceImpl(consumptionRepositoryPort, counterService);
    }

    @Bean
    public CounterRepositoryPort counterRepositoryPort(CounterSpringRepository counterSpringRepository) {
        return new CounterRepositoryImpl(counterSpringRepository);
    }

    @Bean
    public ConsumptionRepositoryPort consumptionRepositoryPort(
            CounterSpringRepository counterSpringRepository,
            ConsumptionSpringRepository consumptionSpringRepository) {
        return new ConsumptionRepositoryImpl(consumptionSpringRepository, counterSpringRepository);
    }

}
