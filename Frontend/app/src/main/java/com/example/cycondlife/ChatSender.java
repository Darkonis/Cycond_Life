package com.example.cycondlife;

import android.os.AsyncTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class ChatSender extends AsyncTask<String, Void, Void> {
    //Socket chat;

    WebSocketClient chat;
    DataOutputStream dos;
    PrintWriter pw;


    @Override
    protected Void doInBackground(String... voids)    {
        //chat = new Socket();

        String message = voids[0];

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

        try {
            pw = new PrintWriter(chat.getSocket().getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
        }   catch (IOException e) {

        }


        return null;
    }
}
