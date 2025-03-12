package net.opensource.stringtransformer.core;

import lombok.RequiredArgsConstructor;
import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import net.opensource.stringtransformer.data.dto.TransformerInvocationDTO;
import net.opensource.stringtransformer.repository.TransformerInvocationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransformerInvocationService {

    private final TransformerInvocationRepository transformerInvocationRepository;

    public void save(String transformerName) {
        TransformerInvocation transformerInvocation = TransformerInvocation.builder()
                                                                           .transformerName(transformerName)
                                                                           .executionTime(Instant.now())
                                                                           .build();
        transformerInvocationRepository.save(transformerInvocation);
    }

    public List<TransformerInvocationDTO> findInvocationsFromTo(Instant from,
                                                                Instant to,
                                                                int pageSize) {
        return transformerInvocationRepository.findPageInBetween(
                from,
                to,
                PageRequest.of(0, pageSize, Sort.by(TransformerInvocation.DOCUMENT_NAME).ascending())
        ).getContent();
    }
}
