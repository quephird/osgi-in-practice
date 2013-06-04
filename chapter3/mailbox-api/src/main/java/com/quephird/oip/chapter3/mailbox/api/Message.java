package com.quephird.oip.chapter3.mailbox.api;

import java.io.InputStream;

public interface Message {

    /**
     *
     * @return The unique message ID within the message's mailbox.
     */
    long getId();

    /**
     *
     * @return A human-readable text summary of the message. In some
     *         messaging systems this would map to tje Subject field.
     */
    String getSummary();

    /**
     *
     * @return The Internet MIME type of the message content.
     */
    String getMimeType();

    /**
     *
     * Access tjhe content of the message.
     *
     * @throws MessageReaderException
     */
    InputStream getContent() throws MessageReaderException;
}
