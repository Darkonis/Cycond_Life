package com.example.cycondlife;

import android.widget.TextView;

import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ChatSenderTest {



    @Test
    public void getReceivedStuff() {
        ChatSender sender = Mockito.spy(new ChatSender());
        Mockito.doReturn("TestSuccess").when(sender).getReceivedStuff();
    }

    /*
    @Test
    public void setChatText() {
        ChatSender sender = Mockito.spy(new ChatSender());
        TextView mockBox = new TextView();

        sender.passChatBox(mockBox);
        sender.receivedText = "TestText";
        sender.setChatText();

        assertEquals(mockBox.getText(), "TestText");
    }
    */

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        ChatSender sender = new ChatSender();
        sender.sendMsg("Nothing");
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        ChatSender sender = new ChatSender();
        sender.connectWebSocket(null);
    }
}