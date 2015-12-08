package daumtrack.kdh.termproject.batch.impl;

import com.google.common.io.Closer;
import daumtrack.kdh.termproject.batch.model.BatchResult;
import daumtrack.kdh.termproject.batch.reader.DataItemReader;
import daumtrack.kdh.termproject.batch.util.BatchSystem;
import daumtrack.kdh.termproject.batch.writer.DataItemProcessor;

/**
 * DefaultBatchSystem.
 *
 * @author 2011108219_�赿��
 *
 * DefaultBatchSystem Ŭ������ �� ���α׷��� ������ �ؽ�Ʈ ���� sort��
 * ������� �ִ� ����� ������ �Ѵ�. �ؽ�Ʈ ������ �����͸� �о�� ��
 * ����� sort ���ָ� ���⼭ ó���Ǵ� ������ ����� ��ȯ���ش�.
 */
public class DefaultBatchSystem implements BatchSystem {
    @Override
    public <T> BatchResult batch(DataItemReader<T> reader, DataItemProcessor<T> processor) {
        BatchProcessStatus status = new BatchProcessStatus();
        status.started();

        Closer closer = Closer.create();
        closer.register(reader);
        closer.register(processor);

        // ������ �б⸦ �����ϸ� ���� ���� ������ ������ ���� �ݺ��Ѵ�.
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


    // ������ ó�� ����� ��ȯ�Ѵ�.
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