package com.quephird.oip.chapter3.mailbox.api;

public class MailboxException extends Exception {
    public MailboxException(String message) {
        super(message);
    }

    public MailboxException(Throwable cause) {
        super(cause);
    }

    public MailboxException(String message, Throwable cause) {
        super(message, cause);
    }
}
