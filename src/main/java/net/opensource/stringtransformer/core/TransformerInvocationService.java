package net.opensource.stringtransformer.core;

import net.opensource.stringtransformer.core.statistics.StatisticsPresenter;
import net.opensource.stringtransformer.data.domain.TransformerInvocation;
import net.opensource.stringtransformer.data.dto.TransformerInvocationDTO;
import net.opensource.stringtransformer.exception.instances.NotFoundException;
import net.opensource.stringtransformer.repository.TransformerInvocationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class TransformerInvocationService {

    private final TransformerInvocationRepository transformerInvocationRepository;
    private final Map<String, StatisticsPresenter> typeToStatisticsPresenter;

    public TransformerInvocationService(TransformerInvocationRepository transformerInvocationRepository,
                                        List<StatisticsPresenter> statisticsPresenters) {
        this.transformerInvocationRepository = transformerInvocationRepository;
        this.typeToStatisticsPresenter = statisticsPresenters.stream()
                                                             .collect(toMap(StatisticsPresenter::supportedType,
                                                                            Function.identity()));
    }

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

    public String calculateStatisticsSinceFor(Instant since, String type) {
        StatisticsPresenter statisticsPresenter = typeToStatisticsPresenter.get(type);

        if (statisticsPresenter == null) {
            throw new NotFoundException("Type " + type + " isn't supported by any existing statistics presenter");
        }

        return statisticsPresenter.presentStatistics(
                transformerInvocationRepository.getTransformerInvocationStatisticsFrom(since)
        );
    }
}
