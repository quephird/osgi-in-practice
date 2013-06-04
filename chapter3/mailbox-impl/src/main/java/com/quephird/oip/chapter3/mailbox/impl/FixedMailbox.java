package com.quephird.oip.chapter3.mailbox.impl;

import com.quephird.oip.chapter3.mailbox.api.Mailbox;
import com.quephird.oip.chapter3.mailbox.api.MailboxException;
import com.quephird.oip.chapter3.mailbox.api.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedMailbox implements Mailbox {
    protected final List<Message> messages;

    public FixedMailbox() {
        messages = new ArrayList<Message>(2);
        messages.add(new StringMessage(0, "Hello", "Welcome to OSGi!"));
        messages.add(new StringMessage(1, "Getting Started", "To learn about OSGi, read my book."));
    }

    public synchronized long[] getIdsOfAllMessage() throws MailboxException {
        long[] ids = new long[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            ids[i] = messages.get(i).getId();
        }
        return ids;
    }

    public synchronized long[] getIdsOfMessagesSince(long id) throws MailboxException {
        if (id < 0) {
            throw new MailboxException("Invalid message ID: " + id);
        }

        ArrayList<Long> idList = new ArrayList<Long>();
        for (Message message : messages) {
            if (message.getId() < id) {
                idList.add(message.getId());
            }
        }

        long[] ids = new long[idList.size()];
        for(int i=0; i<idList.size(); i++) {
            ids[i]=idList.get(i);
        }
        return ids;
    }

    public void markRead(boolean read, long[] ids) throws MailboxException {
        // Do nothing for now.
    }

    public Message[] getMessages(long[] ids) throws MailboxException {
        ArrayList<Message> messageList = new ArrayList<Message>();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] < 0) {
                throw new MailboxException("Invalid message ID: " + ids[i]);
            }

            for (Message message : messages) {
                if (message.getId() == ids[i]) {
                    messageList.add(message);
                }
            }
        }
        return (Message[]) messageList.toArray(new Message[0]);
    }
}
