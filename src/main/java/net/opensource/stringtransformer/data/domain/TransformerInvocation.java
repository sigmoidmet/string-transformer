package net.opensource.stringtransformer.data.domain;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(TransformerInvocation.DOCUMENT_NAME)
@Builder
public record TransformerInvocation(
        @Id
        String id,

        String transformerName,

        @Field(EXECUTION_TIME_NAME)
        Instant executionTime
) {
    public static final String DOCUMENT_NAME = "TransformerInvocation";
    public static final String EXECUTION_TIME_NAME = "executionTime";
}
