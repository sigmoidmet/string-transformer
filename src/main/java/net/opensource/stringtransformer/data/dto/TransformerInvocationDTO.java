package net.opensource.stringtransformer.data.dto;

import java.time.Instant;

public record TransformerInvocationDTO (
        String transformerName,
        Instant executionTime
) {}
