package daumtrack.kdh.termproject.batch;

import java.io.Closeable;

/**
 * DataItemProcessor.
 *
 * @author mitchell.geek
 */
public interface DataItemProcessor<T> extends Closeable {
    /**
     * 데이터 건별 처리
     * <p/>
     * 처리 오류시 런타입예외를 던지도록 한다.
     *
     * @param dataItem
     */
    void process(T dataItem);

    void init() throws Exception;
}
