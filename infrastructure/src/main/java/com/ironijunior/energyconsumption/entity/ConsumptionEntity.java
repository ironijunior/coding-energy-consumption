package com.ironijunior.energyconsumption.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class that represents the Consumption table.
 *
 * @author Ironi Junior Medina
 */
@NoArgsConstructor
@Data
@Entity
public class ConsumptionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "counter_id")
    private CounterEntity counter;

    @NotNull
    @Column(precision = 10, scale = 3)
    @Digits(integer = 10, fraction = 3)
    private BigDecimal amount;

    @NotNull
    @Column
    private LocalDateTime createdAt;

}





