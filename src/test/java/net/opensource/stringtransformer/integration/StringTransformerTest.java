package net.opensource.stringtransformer.integration;

import com.fasterxml.jackson.databind.JsonNode;
import net.opensource.stringtransformer.data.StringTransformRequest;
import net.opensource.stringtransformer.data.TransformerData;
import net.opensource.stringtransformer.data.parameters.RegexBasedMatchesRemovalStringTransformerParameters;
import net.opensource.stringtransformer.data.parameters.RegexBasedMatchesReplacingStringTransformerParameters;
import net.opensource.stringtransformer.integration.harness.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

public class StringTransformerTest extends IntegrationTestBase {

    @Test
    void whenTransformerNotExists_shouldReturn404() {
        StringTransformRequest stringTransformRequest = requestData("can I break you with something not existing?", transformerData("not-existing-transformer"));

        put("/transform", stringTransformRequest)
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void whenValueIsNull_shouldReturn400() {
        StringTransformRequest stringTransformRequest = requestData(
                null,
                transformerData("regex-based-matches-removal-transformer",
                                new RegexBasedMatchesRemovalStringTransformerParameters("123")));

        put("/transform", stringTransformRequest)
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenParametersRequiredByTransformerButNotProvided_shouldReturn400() {
        StringTransformRequest stringTransformRequest = requestData(
                "transform me somehow, i don't know how",
                transformerData("regex-based-matches-removal-transformer"));

        put("/transform", stringTransformRequest)
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenParametersIncomplete_shouldReturn400() {
        StringTransformRequest stringTransformRequest = requestData(
                "transform me somehow, i don't know how",
                transformerData("regex-based-matches-removal-transformer",
                                new RegexBasedMatchesRemovalStringTransformerParameters("")));

        put("/transform", stringTransformRequest)
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void regexBasedMatchesRemovalTransformer() {
        StringTransformRequest stringTransformRequest = requestData(
                "So, i, don't, know, how, to, use, commas, please, remove, all, of, them",
                transformerData("regex-based-matches-removal-transformer",
                                new RegexBasedMatchesRemovalStringTransformerParameters("[,]")));

        putSuccessful("/transform", stringTransformRequest)
                .jsonPath("$").isEqualTo("So i don't know how to use commas please remove all of them");
    }

    @Test
    void regexBasedMatchesReplacingTransformer() {
        StringTransformRequest stringTransformRequest = requestData(
                "I think if you replace my current bank account balance of 0$ with something bigger, I will be rich!",
                transformerData("regex-based-matches-replacing-transformer",
                                new RegexBasedMatchesReplacingStringTransformerParameters("0\\$", "1000000\\$")));

        putSuccessful("/transform", stringTransformRequest)
                .jsonPath("$").isEqualTo("I think if you replace my current bank account balance of 1000000$ with something bigger, I will be rich!");
    }

    @Test
    void upperCaseTransformer() {
        StringTransformRequest stringTransformRequest = requestData(
                "i want to grow bigger!",
                transformerData("upper-case-transformer"));

        putSuccessful("/transform", stringTransformRequest)
                .jsonPath("$").isEqualTo("I WANT TO GROW BIGGER!");
    }

    @Test
    void lowerCaseTransformer() {
        StringTransformRequest stringTransformRequest = requestData(
                "HOW TO DISABLE CAPS LOCK?!",
                transformerData("lower-case-transformer"));

        putSuccessful("/transform", stringTransformRequest)
                .jsonPath("$").isEqualTo("how to disable caps lock?!");
    }

    private StringTransformRequest requestData(String value, TransformerData... transformersData) {
        return new StringTransformRequest(
                value,
                List.of(transformersData)
        );
    }

    private TransformerData transformerData(String name) {
        return new TransformerData(name, null);
    }

    private <T> TransformerData transformerData(String name, T parameters) {
        return new TransformerData(name, objectMapper.convertValue(parameters, JsonNode.class));
    }
}
