package net.opensource.stringtransformer.repository;

import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import net.opensource.stringtransformer.data.dto.TransformerInvocationDTO;
import net.opensource.stringtransformer.data.dto.TransformerInvocationStatistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface TransformerInvocationRepository extends MongoRepository<TransformerInvocation, String> {

    @Query("{ 'executionTime': { $gt: ?0, $lt: ?1 } }")
    Slice<TransformerInvocationDTO> findPageInBetween(Instant from, Instant to, Pageable pageable);

    @Aggregation(pipeline = {
            "{ $match: { executionTime: { $gt: ?0 } } }",
            "{ $group: { _id: { transformerName: '$transformerName', day: { $dateToString: { format: '%Y-%m-%d', date: '$executionTime' } } }, count: { $sum: 1 } } }",
            """
            { $group: {
                _id: '$_id.transformerName',
                totalInvocations: { $sum: '$count' },
                minInvocationsPerDay: { $min: '$count' },
                maxInvocationsPerDay: { $max: '$count' },
                meanInvocationsPerDay: { $avg: '$count' }
              }
            }
            """,
            "{ $project: { transformerName: '$_id', totalInvocations: 1, minInvocationsPerDay: 1, maxInvocationsPerDay: 1, meanInvocationsPerDay: 1, _id: 0 } }"
    })
    List<TransformerInvocationStatistics> getTransformerInvocationStatisticsFrom(Instant since);
}