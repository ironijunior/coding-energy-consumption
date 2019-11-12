package com.ironijunior.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Class that represents the response for the report endpoint.
 *
 * @author Ironi Junior Medina
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ConsumptionResponseDTO {

    private List<ConsumptionInformationDTO> villages;

}
