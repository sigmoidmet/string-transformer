package net.opensource.stringtransformer.data.parameters;

import jakarta.annotation.Nullable;

@Nullable
public record EmptyTransformParameters() {

    public static final EmptyTransformParameters INSTANCE = new EmptyTransformParameters();
}
