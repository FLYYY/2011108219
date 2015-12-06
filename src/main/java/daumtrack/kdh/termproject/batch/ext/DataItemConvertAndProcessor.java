package daumtrack.kdh.termproject.batch.ext;

import daumtrack.kdh.termproject.batch.DataItemProcessor;

import java.io.IOException;

/**
 * CovertingAndProcessor.
 *
 * @author mitchell.geek
 */
public class DataItemConvertAndProcessor<T, S> implements DataItemProcessor<T> {
    private final Converter<T, S> converter;
    private final DataItemProcessor<S> processor;

    public DataItemConvertAndProcessor(Converter<T, S> converter, DataItemProcessor<S> processor) {
        this.converter = converter;
        this.processor = processor;
    }

    @Override
    public final void process(T dataItem) {
        S coverted = converter.convert(dataItem);
        processor.process(coverted);
    }

    @Override
    public void init() throws Exception {
        processor.init();
    }

    @Override
    public void close() throws IOException {
        processor.close();
    }
}
