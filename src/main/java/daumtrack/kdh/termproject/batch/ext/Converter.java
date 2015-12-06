package daumtrack.kdh.termproject.batch.ext;

/**
 * Coverter.
 *
 * @author mitchell.geek
 */
public interface Converter<T, S> {

    S convert(T item);
}
