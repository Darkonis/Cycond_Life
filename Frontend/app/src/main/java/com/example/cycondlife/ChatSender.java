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

public class ChatSender {
    //Socket chat;

    WebSocketClient chat;
    //DataOutputStream dos;
    PrintWriter pw;


    public Void connectWebSocket(URI dest)  {
        //chat = new Socket();

        chat = new WebSocketClient(dest)   {
            public void onMessage(String var1)  {

            }

            public void onOpen(ServerHandshake var1)  {

            }

            public void onClose(int var1, String var2, boolean var3)    {

            }

            public void onError(Exception var1)    {

            }
        };

        chat.connect();

//        try {
//            pw = new PrintWriter(chat.getSocket().getOutputStream());
//            pw.write(message);
//            pw.flush();
//            pw.close();
//        }   catch (IOException e) {
//            e.printStackTrace();
//        }


        return null;
    }

    public void sendMsg(String msg)  {
        chat.send(msg);
    }
}
