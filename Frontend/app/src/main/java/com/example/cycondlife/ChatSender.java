package com.example.cycondlife;

import android.os.AsyncTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class ChatSender extends AsyncTask<Void, Void, Void> {
    //Socket chat;
    
    WebSocketClient chat;
    DataOutputStream dos;
    PrintWriter pw;


    @Override
    protected Void doInBackground(Void... voids)    {
        //chat = new Socket();

        try {
            chat = new WebSocketClient(new URI("wss://echo.websocket.org"))   {
                public void onMessage(String var1)  {

                }

                public void onOpen(ServerHandshake var1)  {

                }

                public void onClose(int var1, String var2, boolean var3)    {

                }

                public void onError(Exception var1)    {

                }
            };
        }   catch (URISyntaxException e)    {
            e.printStackTrace();
        }


        return null;
    }
}
