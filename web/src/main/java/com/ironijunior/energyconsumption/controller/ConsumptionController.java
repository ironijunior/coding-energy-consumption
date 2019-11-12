package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.ConsumptionInformationDTO;
import com.ironijunior.energyconsumption.dto.ConsumptionRequestDTO;
import com.ironijunior.energyconsumption.dto.ConsumptionResponseDTO;
import com.ironijunior.energyconsumption.model.Consumption;
import com.ironijunior.energyconsumption.ports.ConsumptionServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/***
 * Exposes the application REST api.
 *
 * @author Ironi Junior Medina
 */
@RestController
public class ConsumptionController {

    private ConsumptionServicePort consumptionService;

    @Autowired
    ConsumptionController(ConsumptionServicePort consumptionService) {
        this.consumptionService = consumptionService;
    }

    /**
     * Method responsible for saving the consumption for a counter.
     *
     * @param request data to be saved as consumption.
     * @return Response
     */
    @PostMapping("/counter_callback")
    public ResponseEntity<Void> saveConsumption(@Validated @RequestBody ConsumptionRequestDTO request) {
        consumptionService.sendCounterCallback(request.getCounterId(), request.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Method responsible for returning the report os consumption for the specified duration
     *
     * @param duration Duration of the report
     * @return Response
     */
    @GetMapping("/consumption_report")
    public ResponseEntity<ConsumptionResponseDTO> getConsumptionReport( @RequestParam String duration) {
        if (Objects.isNull(duration)) {
            throw new IllegalArgumentException("duration should not be null");
        }

        return new ResponseEntity<ConsumptionResponseDTO>(
                convertToDTO(
                        consumptionService.obtainConsumptionReport(duration)),
                HttpStatus.OK);
    }

    private ConsumptionResponseDTO convertToDTO(List<Consumption> consumptions) {
        ConsumptionResponseDTO response = new ConsumptionResponseDTO();
        response.setVillages(
            consumptions.stream()
                    .map(consumption -> ConsumptionInformationDTO.builder()
                                .villageName(consumption.getCounter().getName())
                                .consumption(consumption.getConsumption())
                                .build())
                    .collect(Collectors.toList())
        );
        return response;
    }
}
