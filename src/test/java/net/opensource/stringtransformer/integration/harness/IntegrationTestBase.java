package net.opensource.stringtransformer.integration.harness;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;


@SuppressWarnings("unused") // I just copied it from another my project, maybe something will be useful, maybe not, idk
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public abstract class IntegrationTestBase  {

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected ObjectMapper objectMapper;


    protected <T> T getSuccessful(String path, Class<T> type) {
        return get(path)
                .expectStatus().is2xxSuccessful()
                .expectBody(type)
                .returnResult()
                .getResponseBody();
    }

    protected WebTestClient.BodyContentSpec getSuccessful(String path) {
        return get(path)
                .expectStatus().is2xxSuccessful()
                .expectBody();
    }

    protected WebTestClient.ResponseSpec get(String path) {
        return webTestClient
                .get()
                .uri("/api" + path)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    protected <T> T postSuccessful(String path, Object body, Class<T> responseClass) {
        return post(path, body)
                .expectStatus().is2xxSuccessful()
                .expectBody(responseClass)
                .returnResult()
                .getResponseBody();
    }

    protected WebTestClient.BodyContentSpec postSuccessful(String path, Object body) {
        return post(path, body)
                .expectStatus().is2xxSuccessful()
                .expectBody();
    }

    protected WebTestClient.ResponseSpec post(String path, Object body) {
        return webTestClient
                .post()
                .uri("/api" + path)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange();
    }

    protected WebTestClient.BodyContentSpec putSuccessful(String path, Object body) {
        return put(path, body)
                .expectStatus().is2xxSuccessful()
                .expectBody();
    }

    protected <T> T putSuccessful(String path, Object body, Class<T> responseClass) {
        return put(path, body)
                .expectStatus().is2xxSuccessful()
                .expectBody(responseClass)
                .returnResult()
                .getResponseBody();
    }

    protected WebTestClient.ResponseSpec put(String path, Object body) {
        return webTestClient
                .put()
                .uri("/api" + path)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange();
    }
}
