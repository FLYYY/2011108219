package daumtrack.kdh.termproject.batch.impl;

import com.google.common.io.Closer;
import daumtrack.kdh.termproject.batch.BatchResult;
import daumtrack.kdh.termproject.batch.DataItemReader;
import daumtrack.kdh.termproject.batch.BatchSystem;
import daumtrack.kdh.termproject.batch.DataItemProcessor;

/**
 * DefaultBatchSystem.
 *
 * @author mitchell.geek
 */
public class DefaultBatchSystem implements BatchSystem {
    @Override
    public <T> BatchResult batch(DataItemReader<T> reader, DataItemProcessor<T> processor) {
        BatchProcessStatus status = new BatchProcessStatus();
        status.started();

        Closer closer = Closer.create();
        closer.register(reader);
        closer.register(processor);

        try {
            reader.init();
            processor.init();
            while (reader.hasNext()) {
                T item = reader.next();
                processor.process(item);
                status.processAnItem();
            }
            status.finished();
        } catch (Exception e) {
            e.printStackTrace();
            status.errorOccurred(e);
        } finally {
            try {
                closer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertToResult(status);
    }

    private BatchResult convertToResult(BatchProcessStatus status) {
        return new BatchResult(
                status.getStartedAt(),
                status.getFinishedAt(),
                status.getState() == BatchProcessStatus.State.FINISHED,
                status.getProcessedCount(),
                status.getException()
        );
    }

}
