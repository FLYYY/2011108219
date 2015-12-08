package daumtrack.kdh.termproject.batch.impl;

import com.google.common.io.Closer;
import daumtrack.kdh.termproject.batch.model.BatchResult;
import daumtrack.kdh.termproject.batch.reader.DataItemReader;
import daumtrack.kdh.termproject.batch.util.BatchSystem;
import daumtrack.kdh.termproject.batch.writer.DataItemProcessor;

/**
 * DefaultBatchSystem.
 *
 * @author 2011108219_김동훈
 *
 * DefaultBatchSystem 클래스는 본 프로그램의 목적인 텍스트 파일 sort를
 * 실행시켜 주는 실행부 역할을 한다. 텍스트 파일의 데이터를 읽어와 순
 * 서대로 sort 해주며 여기서 처리되는 데이터 결과를 반환해준다.
 */
public class DefaultBatchSystem implements BatchSystem {
    @Override
    public <T> BatchResult batch(DataItemReader<T> reader, DataItemProcessor<T> processor) {
        BatchProcessStatus status = new BatchProcessStatus();
        status.started();

        Closer closer = Closer.create();
        closer.register(reader);
        closer.register(processor);

        // 데이터 읽기를 시작하며 다음 줄의 내용이 없을때 까지 반복한다.
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


    // 데이터 처리 결과를 반환한다.
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