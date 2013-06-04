package com.quephird.oip.chapter3.mailbox.api;

public interface Mailbox {

    public static final String NAME_PROPERTY = "mailboxName";

    /**
     * Retrieve the IDs of all messages available in the mailbox.
     *
     * @return An array of message IDs.
     * @throws MailboxException
     */
    long[] getIdsOfAllMessage() throws MailboxException;

    /**
     * Retrieve the IDs of all messages received after the specified message.
     * @param id The message ID of interest.
     * @return An array of message IDs.
     * @throws MailboxException
     */
    long[] getIdsOfMessagesSince(long id) throws MailboxException;

    /**
     * Mark the specified messages as read/unread on the backend message source,
     * where supported, e.g., IMAP supports this feature.
     *
     * @param read Whether the specified messages have been read.
     * @param ids An array of message IDs.
     * @throws MailboxException
     */
    void markRead(boolean read, long[] ids) throws MailboxException;

    /**
     * Retrieve the specified messages.
     *
     * @param ids The IDs of the messages to be retrieved.
     * @return An array of Messages.
     * @throws MailboxException
     */
    Message[] getMessages(long[] ids) throws MailboxException;
}
