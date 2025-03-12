package net.opensource.stringtransformer.core.statistics;

import net.opensource.stringtransformer.data.dto.TransformerInvocationStatistics;

import java.util.List;

public interface StatisticsPresenter {

    String presentStatistics(List<TransformerInvocationStatistics> transformerInvocationStatistics);

    String supportedType();
}
