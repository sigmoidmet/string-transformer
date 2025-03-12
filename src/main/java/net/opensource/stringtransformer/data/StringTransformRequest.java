package net.opensource.stringtransformer.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StringTransformRequest(
   @NotNull String value,
   @NotEmpty List<@Valid TransformerData> transformersData
) {}
