package net.opensource.stringtransformer.core.statistics;

import net.opensource.stringtransformer.data.dto.TransformerInvocationStatistics;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlainTextStatisticsPresenter implements StatisticsPresenter {

    @Override
    public String presentStatistics(List<TransformerInvocationStatistics> transformerInvocationStatistics) {
        return transformerInvocationStatistics.toString();
    }

    @Override
    public String supportedType() {
        return "text/plain";
    }
}
