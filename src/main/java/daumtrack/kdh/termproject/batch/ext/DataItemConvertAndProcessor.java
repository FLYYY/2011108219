package daumtrack.kdh.termproject.batch.ext;

import daumtrack.kdh.termproject.batch.writer.DataItemProcessor;

import java.io.IOException;

/**
 * CovertingAndProcessor.
 *
 * @author 2011108219_±èµ¿ÈÆ
 *
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
