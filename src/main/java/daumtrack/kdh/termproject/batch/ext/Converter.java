package daumtrack.kdh.termproject.batch.ext;

/**
 * Coverter.
 *
 * @author 2011108219_±èµ¿ÈÆ
 *
 *
 */
public interface Converter<T, S> {

    S convert(T item);
}
