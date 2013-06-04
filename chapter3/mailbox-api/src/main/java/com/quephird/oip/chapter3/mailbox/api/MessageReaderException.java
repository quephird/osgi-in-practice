package com.quephird.oip.chapter3.mailbox.api;

public class MessageReaderException extends Exception {
    private static final long serialVersionUid = 1L;

    public MessageReaderException(String message) {
        super(message);
    }

    public MessageReaderException(Throwable cause) {
        super(cause);
    }

    public MessageReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
