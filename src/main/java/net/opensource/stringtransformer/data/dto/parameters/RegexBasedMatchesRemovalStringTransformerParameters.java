package net.opensource.stringtransformer.data.dto.parameters;

import jakarta.validation.constraints.NotBlank;

public record RegexBasedMatchesRemovalStringTransformerParameters (
        @NotBlank String regexPattern
) {}
