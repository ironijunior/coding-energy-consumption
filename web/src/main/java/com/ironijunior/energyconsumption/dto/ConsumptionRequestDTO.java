package com.ironijunior.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Class that represent the body request of a Consumption.
 *
 * @author Ironi Junior Medina
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumptionRequestDTO {

    @JsonProperty("counter_id")
    private String counterId;
    @JsonProperty("amount")
    private BigDecimal amount;

}
