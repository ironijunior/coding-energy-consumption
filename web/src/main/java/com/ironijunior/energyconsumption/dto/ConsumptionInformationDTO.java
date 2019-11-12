package com.ironijunior.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Class that represents the Consumption information to be exposed.
 *
 * @author Ironi Junior Medina
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonPropertyOrder({"village_name", "consumption"})
public class ConsumptionInformationDTO {

    @JsonProperty("village_name")
    private String villageName;
    private BigDecimal consumption;

}
