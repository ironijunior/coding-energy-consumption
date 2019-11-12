package com.ironijunior.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Class that represent the Counter information to be exposed.
 *
 * @author Ironi Junior Medina
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class CounterDTO {

    private String id;
    @JsonProperty(value = "village_name")
    private String villageName;

}
