package de.hawh.ld.fixedsizequeue;

public class QueueEmptyException extends RuntimeException {

    public QueueEmptyException(String errorMsg) {
        super(errorMsg);
    }
}