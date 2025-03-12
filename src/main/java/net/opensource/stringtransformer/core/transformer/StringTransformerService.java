package net.opensource.stringtransformer.core.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.opensource.stringtransformer.data.StringTransformRequest;
import net.opensource.stringtransformer.data.TransformerData;
import net.opensource.stringtransformer.exception.instances.StringTransformerNotFound;
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
            value = applyTransformer(transformRequest.value(), getTransformer(data), data.parameters());
        }

        return value;
    }

    private StringTransformer<?> getTransformer(TransformerData data) {
        StringTransformer<?> stringTransformer = nameToStringTransformer.get(data.transformerName());
        if (stringTransformer == null) {
            throw new StringTransformerNotFound(data.transformerName() + " not exists.");
        }
        return stringTransformer;
    }

    private <T> String applyTransformer(String value,
                                        StringTransformer<T> stringTransformer,
                                        JsonNode rawParameters) {
        T parameters = stringTransformer.defaultParameters();

        if (!rawParameters.isNull()) {
            parameters = objectMapper.convertValue(rawParameters, stringTransformer.parametersType());
        }

        return stringTransformer.transform(value, parameters);
    }
}
