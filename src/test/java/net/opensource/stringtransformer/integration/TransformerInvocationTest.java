package net.opensource.stringtransformer.integration;

import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import net.opensource.stringtransformer.integration.harness.IntegrationTestBase;
import net.opensource.stringtransformer.repository.TransformerInvocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class TransformerInvocationTest extends IntegrationTestBase {

    @Autowired
    TransformerInvocationRepository transformerInvocationRepository;

    Instant from = Instant.now().minusSeconds(200);
    Instant to = Instant.now().plusSeconds(200);

    @BeforeEach
    public void beforeEach() {
        transformerInvocationRepository.deleteAll();
        transformerInvocationRepository.save(
                TransformerInvocation.builder()
                                     .transformerName("you won't see me anyway")
                                     .executionTime(from.minusSeconds(300))
                                     .build()
        );
        transformerInvocationRepository.save(
                TransformerInvocation.builder()
                                     .transformerName("i worked so many times!")
                                     .executionTime(from.plusSeconds(1))
                                     .build()
        );
        transformerInvocationRepository.save(
                TransformerInvocation.builder()
                                     .transformerName("i'm just a middle man")
                                     .executionTime(from.plusSeconds(10))
                                     .build()
        );
        transformerInvocationRepository.save(
                TransformerInvocation.builder()
                                     .transformerName("i worked so many times!")
                                     .executionTime(to.minusSeconds(1))
                                     .build()
        );
        transformerInvocationRepository.save(
                TransformerInvocation.builder()
                                     .transformerName("you won't see me anyway")
                                     .executionTime(to.plusSeconds(300))
                                     .build()
        );
    }

    @Test
    public void findAllExecutionsInBetween_whenNoSizeProvided_shouldUseDefault() {
        getSuccessful("/transformer-invocations?from=" + from + "&to=" + to)
                .jsonPath("$[0].transformerName").isEqualTo("i worked so many times!")
                .jsonPath("$[1].transformerName").isEqualTo("i'm just a middle man")
                .jsonPath("$[2].transformerName").isEqualTo("i worked so many times!")
                .jsonPath("$[3]").doesNotExist();
    }

    @Test
    public void findAllExecutionsInBetween_whenProvidedCorrectSize_shouldUseIt() {
        getSuccessful("/transformer-invocations?from=" + from + "&to=" + to + "&pageSize=1")
                .jsonPath("$[0].transformerName").isEqualTo("i worked so many times!")
                .jsonPath("$[1]").doesNotExist();
    }

    @Test
    public void findAllExecutionsInBetween_whenProvidedIncorrectSize_shouldReturn400() {
        get("/transformer-invocations?from=" + from + "&to=" + to + "&pageSize=100000000")
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
