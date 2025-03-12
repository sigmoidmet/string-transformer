package net.opensource.stringtransformer.core.transformer;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface StringTransformer<T> {

    String transform(String value, @Valid @NotNull T parameters);

    TypeReference<T> parametersType();

    String transformerName();

    // If your parameters are optional, you need to override it, otherwise it will result in 400 client error
    default T defaultParameters() {
        return null;
    }
}
