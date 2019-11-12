package com.ironijunior.energyconsumption.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represent the body request of a Counter.
 *
 * @author Ironi Junior Medina
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterRequestDTO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

}
