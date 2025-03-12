package net.opensource.stringtransformer.data.dto.parameters;

import jakarta.annotation.Nullable;

@Nullable
public record EmptyTransformParameters() {

    public static final EmptyTransformParameters INSTANCE = new EmptyTransformParameters();
}
