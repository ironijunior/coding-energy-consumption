package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.CounterDTO;
import com.ironijunior.energyconsumption.dto.CounterRequestDTO;
import com.ironijunior.energyconsumption.model.Counter;
import com.ironijunior.energyconsumption.ports.CounterServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Class to exposes the endpoints for the Counter operations.
 *
 * @author Ironi Junior Medina
 */
@RestController
public class CounterController {

    private CounterServicePort counterService;

    CounterController(CounterServicePort counterService) {
        this.counterService = counterService;
    }

    /**
     * Endpoint for getting information about a counter
     * @param id counter identifier
     * @return Counter information
     */
    @GetMapping("/counter")
    public ResponseEntity<CounterDTO> getCounter(@RequestParam String id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id should not be null");
        }
        return ResponseEntity.ok(convertToDTO(counterService.obtainCounterInformation(id)));
    }

    /**
     * Endpoint for writing a new counter
     * @param request counter information
     *
     */
    @PostMapping("/counter")
    public ResponseEntity<Void> saveCounter(@RequestBody CounterRequestDTO request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("request should not be null");
        }
        counterService.saveCounter(convertFromDTO(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private Counter convertFromDTO(CounterRequestDTO request) {
        Counter counter = new Counter();
        counter.setId(request.getId());
        counter.setName(request.getName());
        return counter;
    }

    private CounterDTO convertToDTO(Counter counter) {
        return CounterDTO.builder()
                .id(counter.getId())
                .villageName(counter.getName())
                .build();
    }

}
