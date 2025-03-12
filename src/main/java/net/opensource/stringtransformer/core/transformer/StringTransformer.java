package net.opensource.stringtransformer.core.transformer;

import jakarta.annotation.Nullable;

public interface StringTransformer<T> {

    void validateParameters(T parameters);

    String transform(String value, @Nullable T parameters);

    Class<T> parametersType();

    String transformerName();
}
