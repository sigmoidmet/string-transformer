package net.opensource.stringtransformer.controller;

import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import net.opensource.stringtransformer.core.TransformerInvocationService;
import net.opensource.stringtransformer.data.dto.TransformerInvocationDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/transformer-invocations")
@RequiredArgsConstructor
@Validated
public class TransformerInvocationController {

    private final TransformerInvocationService transformerInvocationService;

    @GetMapping
    public List<TransformerInvocationDTO> getTransformerInvocationsInBetween(
            @RequestParam Instant from,
            @RequestParam Instant to,
            @Max(10000) @RequestParam(required = false, defaultValue = "1000") int pageSize
    ) {
        return transformerInvocationService.findInvocationsFromTo(from, to, pageSize);
    }

    @GetMapping("/statistics")
    public ResponseEntity<String> getTransformerInvocationsStatistics(@RequestParam Instant from,
                                                                      @RequestParam String expectedType) {
        String statistics = transformerInvocationService.calculateStatisticsSinceFor(from, expectedType);
        return ResponseEntity.ok().contentType(new MediaType("text", "plain")).body(statistics);
    }
}
