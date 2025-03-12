package net.opensource.stringtransformer.core.transformer.instances;

import com.fasterxml.jackson.core.type.TypeReference;
import net.opensource.stringtransformer.core.transformer.StringTransformer;
import net.opensource.stringtransformer.data.parameters.RegexBasedMatchesReplacingStringTransformerParameters;
import org.springframework.stereotype.Component;

@Component
public class RegexBasedMatchesReplacingStringTransformer implements StringTransformer<RegexBasedMatchesReplacingStringTransformerParameters> {

    @Override
    public String transform(String value, RegexBasedMatchesReplacingStringTransformerParameters parameters) {
        return value.replaceAll(parameters.regexPattern(), parameters.replacement());
    }

    @Override
    public TypeReference<RegexBasedMatchesReplacingStringTransformerParameters> parametersType() {
        return new TypeReference<>() {};
    }

    @Override
    public String transformerName() {
        return "regex-based-matches-replacing-transformer";
    }
}
