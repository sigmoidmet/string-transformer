package net.opensource.stringtransformer.repository;

import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import net.opensource.stringtransformer.data.dto.TransformerInvocationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;

public interface TransformerInvocationRepository extends MongoRepository<TransformerInvocation, String> {

    @Query("{ 'executionTime': { $gt: ?0, $lt: ?1 } }")
    Slice<TransformerInvocationDTO> findPageInBetween(Instant from, Instant to, Pageable pageable);
}