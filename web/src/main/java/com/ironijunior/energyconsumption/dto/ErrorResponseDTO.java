package com.ironijunior.energyconsumption.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Class that represent the error response for the REST api.
 *
 * @author Ironi Junior Medina
 */
@Builder
@Data
public class ErrorResponseDTO {

    private int status;
    private String message;

}
