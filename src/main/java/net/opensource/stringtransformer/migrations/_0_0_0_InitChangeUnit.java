package net.opensource.stringtransformer.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "init-database", order = "0.0.0", transactional = false)
@RequiredArgsConstructor
public class _0_0_0_InitChangeUnit {

    private final MongoTemplate mongoTemplate;


    // We don't work with data, only changing schema so we don't need them
    @Execution
    public void execution() {
        mongoTemplate.createCollection(TransformerInvocation.DOCUMENT_NAME,
                                       CollectionOptions.timeSeries(TransformerInvocation.EXECUTION_TIME_NAME));
    }
    @RollbackExecution
    public void rollbackExecution() {
        mongoTemplate.dropCollection(TransformerInvocation.DOCUMENT_NAME);
    }
}
