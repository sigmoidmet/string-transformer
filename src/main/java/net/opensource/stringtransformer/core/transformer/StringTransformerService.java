package net.opensource.stringtransformer.core.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import net.opensource.stringtransformer.data.StringTransformRequest;
import net.opensource.stringtransformer.data.TransformerData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class StringTransformerService {

    private final Map<String, StringTransformer<?>> nameToStringTransformer;
    private final ObjectMapper objectMapper;

    public StringTransformerService(List<StringTransformer<?>> nameToStringTransformer, ObjectMapper objectMapper) {
        this.nameToStringTransformer = nameToStringTransformer.stream()
                                                              .collect(toMap(StringTransformer::transformerName,
                                                                             Function.identity()));
        this.objectMapper = objectMapper;
    }

    public String transform(StringTransformRequest transformRequest) {
        String value = transformRequest.value();

        for (TransformerData data : transformRequest.transformersData()) {
            value = applyTransformer(transformRequest.value(),
                                     nameToStringTransformer.get(data.transformerName()),
                                     data.parameters());
        }

        return value;
    }

    private <T> String applyTransformer(String value,
                                        StringTransformer<T> stringTransformer,
                                        @Nullable JsonNode rawParameters) {
        T parameters = null;

        if (rawParameters != null) {
            parameters = objectMapper.convertValue(rawParameters, stringTransformer.parametersType());
        }

        stringTransformer.validateParameters(parameters);

        return stringTransformer.transform(value, parameters);
    }
}
