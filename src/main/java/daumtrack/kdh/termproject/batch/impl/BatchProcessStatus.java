package daumtrack.kdh.termproject.batch.impl;

import java.util.Date;

/**
 * BatchProcessStatus.
 *
 * @author 2011108219_김동훈
 *
 * sort가 실행되기 위해 각 상태를 반환해주는 역할을 한다.
 */
public class BatchProcessStatus {
    private Date startedAt;
    private Date finishedAt;
    private State state;
    private int processedCount = 0;
    private Exception exception;

    enum State {
        INIT, STARTED, FINISHED, ERRORED
    }

    public BatchProcessStatus() {
        this.state = State.INIT;
    }

    public void started() {
        this.startedAt = new Date();
        this.state = State.STARTED;
    }

    public void processAnItem() {
        this.processedCount++;
    }

    public void finished() {
        this.finishedAt = new Date();
        this.state = State.FINISHED;
    }

    public void errorOccurred(Exception exception) {
        this.finishedAt = new Date();
        this.state = State.ERRORED;
        this.exception = exception;
    }


    public Date getStartedAt() {
        return startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public State getState() {
        return state;
    }

    public int getProcessedCount() {
        return processedCount;
    }

    public Exception getException() {
        return exception;
    }
}
