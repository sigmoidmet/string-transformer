package net.opensource.stringtransformer.core.transformer.instances;

import net.opensource.stringtransformer.core.transformer.ParameterlessStringTransformer;
import org.springframework.stereotype.Component;

@Component
public class LowerCaseStringTransformer implements ParameterlessStringTransformer {

    @Override
    public String transform(String value) {
        return value.toLowerCase();
    }

    @Override
    public String transformerName() {
        return "lower-case-transformer";
    }
}
