package net.opensource.stringtransformer.core.transformer.instances;

import net.opensource.stringtransformer.core.transformer.ParameterlessStringTransformer;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseStringTransformer implements ParameterlessStringTransformer {

    @Override
    public String transform(String value) {
        return value.toUpperCase();
    }

    @Override
    public String transformerName() {
        return "upper-case-transformer";
    }
}
