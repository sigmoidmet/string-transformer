package net.opensource.stringtransformer.data.dto;

public record TransformerInvocationStatistics (
        String transformerName,
        int meanInvocationsPerDay,
        int maxInvocationsPerDay,
        int minInvocationsPerDay,
        int totalInvocations
) {}
