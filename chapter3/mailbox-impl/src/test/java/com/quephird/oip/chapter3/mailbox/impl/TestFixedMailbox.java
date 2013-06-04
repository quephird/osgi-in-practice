package com.quephird.oip.chapter3.mailbox.impl;

import com.quephird.oip.chapter3.mailbox.api.MailboxException;
import com.quephird.oip.chapter3.mailbox.api.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestFixedMailbox {
    private FixedMailbox fmb;
    private Message[] messages;
    private long[] ids;

    @Before
    public void setUp() {
        fmb = new FixedMailbox();
        messages = new Message[10];
        ids = new long[10];
    }

    @Test(expected = MailboxException.class)
    public void testLookForMessageWithBadId() throws Exception {
        messages = (Message[]) fmb.getMessages(new long[]{-1});
    }

    @Test
    public void testLookForMessageWhichShouldNotBeThere() throws Exception {
        messages = (Message[]) fmb.getMessages(new long[]{42});
        assertEquals(messages.length, 0);
    }

    @Test
    public void testLookForMessageWhichShouldBeThere() throws Exception {
        messages = (Message[]) fmb.getMessages(new long[]{1});
        assertEquals(messages.length, 1);
    }

    @Test
    public void testLookForMultipleMessagesWhichShouldBeThere() throws Exception {
        messages = (Message[]) fmb.getMessages(new long[]{0, 1});
        assertEquals(messages.length, 2);
    }

    @Test(expected = MailboxException.class)
    public void testGetMessagesSinceWithBadId() throws Exception {
        ids = fmb.getIdsOfMessagesSince(-1);
    }

    @Test
    public void testGetMessagesSinceFirstIdShouldBeZero() throws Exception {
        ids = fmb.getIdsOfMessagesSince(0);
        assertEquals(ids.length, 0);
    }

    @Test
    public void testGetMessagesSinceLastIdShouldNotBeZero() throws Exception {
        ids = fmb.getIdsOfMessagesSince(1);
        assertNotSame(ids.length, 0);
    }
}