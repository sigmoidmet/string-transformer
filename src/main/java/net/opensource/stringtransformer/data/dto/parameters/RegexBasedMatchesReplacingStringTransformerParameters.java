package net.opensource.stringtransformer.data.dto.parameters;

import jakarta.validation.constraints.NotBlank;

public record RegexBasedMatchesReplacingStringTransformerParameters (
        @NotBlank String regexPattern,
        @NotBlank String replacement
) {}
