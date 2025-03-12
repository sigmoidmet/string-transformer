package net.opensource.stringtransformer.core.statistics;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import net.opensource.stringtransformer.data.dto.TransformerInvocationStatistics;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvStatisticsPresenter implements StatisticsPresenter {

    private final ObjectWriter csvWriter;

    public CsvStatisticsPresenter(CsvMapper csvMapper) {
        CsvSchema schema = csvMapper.schemaFor(TransformerInvocationStatistics.class).withHeader();
        this.csvWriter = csvMapper.writer(schema);
    }

    @Override
    @SneakyThrows
    public String presentStatistics(List<TransformerInvocationStatistics> transformerInvocationStatistics) {
        return csvWriter.writeValueAsString(transformerInvocationStatistics);
    }

    @Override
    public String supportedType() {
        return "text/csv";
    }
}
