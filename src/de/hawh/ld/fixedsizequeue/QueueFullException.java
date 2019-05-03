package de.hawh.ld.fixedsizequeue;

public class QueueFullException extends RuntimeException {

    public QueueFullException(String errorMsg) {
        super(errorMsg);
    }
}

