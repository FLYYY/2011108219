package daumtrack.kdh.termproject.batch;

import java.io.Closeable;

/**
 * DataItemReader.
 *
 * @author mitchell.geek
 */
public interface DataItemReader<T> extends Closeable {

    void init() throws Exception;

    boolean hasNext();

    T next() throws NoMoreDataException;

}
