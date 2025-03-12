package net.opensource.stringtransformer.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record StringTransformRequest(
   @NotBlank String value,
   @NotEmpty List<@Valid TransformerData> transformersData
) {}
