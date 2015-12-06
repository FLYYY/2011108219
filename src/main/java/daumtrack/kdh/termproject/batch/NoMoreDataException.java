package daumtrack.kdh.termproject.batch;

/**
 * NoMoreDataException.
 *
 * @author mitchell.geek
 */
public class NoMoreDataException extends RuntimeException {
    public NoMoreDataException() {
    }

    public NoMoreDataException(String message) {
        super(message);
    }

    public NoMoreDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreDataException(Throwable cause) {
        super(cause);
    }

    public NoMoreDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
