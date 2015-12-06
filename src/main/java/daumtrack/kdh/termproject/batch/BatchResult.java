package daumtrack.kdh.termproject.batch;

import java.util.Date;

/**
 * BatchResult.
 *
 * @author mitchell.geek
 */
public class BatchResult {
    private final Date startedAt;
    private final Date finishedAt;
    private final boolean sucessed;
    private final int processedItems;
    private final Exception exception;

    public BatchResult(Date startedAt, Date finishedAt, boolean sucessed, int processedItems, Exception exception) {
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.sucessed = sucessed;
        this.processedItems = processedItems;
        this.exception = exception;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public boolean isSucessed() {
        return sucessed;
    }

    public int getProcessedItems() {
        return processedItems;
    }

    public Exception getException() {
        return exception;
    }

    public long ellapsedTimeMills() {
        Date baseTime = finishedAt;
        if (baseTime == null) {
            baseTime = new Date();
        }
        return baseTime.getTime() - startedAt.getTime();
    }

    public String prettyReport() {

        return new StringBuilder()
                .append("# Processing Result:").append("\n")
                .append(" - started at: ").append(startedAt).append("\n")
                .append(" - finished at: ").append(finishedAt).append("\n")
                .append(" - processed items: ").append(processedItems).append("\n")
                .append(" - ellapsed time: ").append(ellapsedTimeMills()).append(" msec").append("\n")
                .toString();
    }
}
