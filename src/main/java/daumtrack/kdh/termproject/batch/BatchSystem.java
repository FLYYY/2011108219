package daumtrack.kdh.termproject.batch;

/**
 * BatchUtil.
 *
 * @author mitchell.geek
 */
public interface BatchSystem {
    <T> BatchResult batch(DataItemReader<T> reader, DataItemProcessor<T> processor);
}