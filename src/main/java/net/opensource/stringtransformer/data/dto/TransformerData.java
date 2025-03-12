package net.opensource.stringtransformer.data.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;

public record TransformerData (
        @NotNull String transformerName,
        JsonNode parameters
) {}
