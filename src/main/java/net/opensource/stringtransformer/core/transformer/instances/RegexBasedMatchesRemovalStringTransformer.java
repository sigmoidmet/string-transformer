package net.opensource.stringtransformer.core.transformer.instances;

import com.fasterxml.jackson.core.type.TypeReference;
import net.opensource.stringtransformer.core.transformer.StringTransformer;
import net.opensource.stringtransformer.data.dto.parameters.RegexBasedMatchesRemovalStringTransformerParameters;
import org.springframework.stereotype.Component;

@Component
public class RegexBasedMatchesRemovalStringTransformer implements StringTransformer<RegexBasedMatchesRemovalStringTransformerParameters> {

    @Override
    public String transform(String value, RegexBasedMatchesRemovalStringTransformerParameters parameters) {
        return value.replaceAll(parameters.regexPattern(), "");
    }

    @Override
    public TypeReference<RegexBasedMatchesRemovalStringTransformerParameters> parametersType() {
        return new TypeReference<>() {};
    }

    @Override
    public String transformerName() {
        return "regex-based-matches-removal-transformer";
    }
}
