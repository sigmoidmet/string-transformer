package net.opensource.stringtransformer.core.transformer;

import com.fasterxml.jackson.core.type.TypeReference;
import net.opensource.stringtransformer.data.parameters.EmptyTransformParameters;

public interface ParameterlessStringTransformer extends StringTransformer<EmptyTransformParameters> {

    @Override
    default String transform(String value, EmptyTransformParameters parameters) {
        return transform(value);
    }

    String transform(String value);

    @Override
    default TypeReference<EmptyTransformParameters> parametersType() {
        return new TypeReference<>(){};
    }

    @Override
    default EmptyTransformParameters defaultParameters() {
        return EmptyTransformParameters.INSTANCE;
    }
}
