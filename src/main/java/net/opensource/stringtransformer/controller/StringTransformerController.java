package net.opensource.stringtransformer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.opensource.stringtransformer.core.transformer.StringTransformerService;
import net.opensource.stringtransformer.data.StringTransformRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class StringTransformerController {

    private final StringTransformerService transformerService;

    @PutMapping("/api/transform")
    public String transform(@RequestBody @Valid StringTransformRequest stringTransformRequest) {
        return transformerService.transform(stringTransformRequest);
    }
}
