package com.quephird.oip.chapter3.mailbox.impl;

import com.quephird.oip.chapter3.mailbox.api.Message;
import com.quephird.oip.chapter3.mailbox.api.MessageReaderException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringMessage implements Message {
    private static final String MIME_TYPE = "text/plain";

    private final long id;
    private final String subject;
    private final String text;

    public StringMessage(long id, String subject, String text) {
        this.id = id;
        this.subject = subject;
        this.text = text;
    }

    public long getId() {
        return this.id;
    }

    public String getSummary() {
        return this.subject;
    }

    public String getMimeType() {
        return MIME_TYPE;
    }

    public InputStream getContent() throws MessageReaderException {
        return new ByteArrayInputStream(text.getBytes());
    }
}
