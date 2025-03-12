package net.opensource.stringtransformer.data;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record TransformerData (
        @NotNull
   String transformerName,

   @Nullable
   JsonNode parameters
) {}
