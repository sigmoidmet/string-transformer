package net.opensource.stringtransformer.core.transformer;

import jakarta.annotation.Nullable;

public interface ParameterlessStringTransformer extends StringTransformer<Void> {

    @Override
    default void validateParameters(@Nullable Void parameters) {
        // we don't care what parameters are given as we don't expect any parameters at all
    }

    @Override
    default String transform(String value, Void parameters) {
        return transform(value);
    }

    String transform(String value);

    @Override
    default Class<Void> parametersType() {
        return Void.class;
    }
}
