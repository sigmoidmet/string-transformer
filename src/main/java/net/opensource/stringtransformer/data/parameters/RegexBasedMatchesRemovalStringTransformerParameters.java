package net.opensource.stringtransformer.data.parameters;

import jakarta.validation.constraints.NotBlank;

public record RegexBasedMatchesRemovalStringTransformerParameters (
        @NotBlank String regexPattern
) {}
