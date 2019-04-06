package com.example.cycondlife;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ChatSender {
    //Socket chat;

    String receivedText = "";
    WebSocketClient chat;
    TextView chatBox;
    Activity chatActivity;
    //DataOutputStream dos;
    //PrintWriter pw;
    //Toast noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT);


    public Void connectWebSocket(URI dest) {
        //chat = new Socket();
        receivedText += "  connecting...";
        setChatText();

        chat = new WebSocketClient(dest)  {
            public void onMessage(String var1)  {
                receivedText += "\r\n" + "  Username: " + var1;
                //chatBox.setText("");
                setChatText();
                Log.i("CyLife Websocket", var1);
            }

            public void onOpen(ServerHandshake var1)  {
                receivedText = "  Welcome to the global chat room!";
                setChatText();
            }

            public void onClose(int var1, String var2, boolean var3)    {
                receivedText += "\r\n" + "  disconnected from chat";
                setChatText();
            }

            public void onError(Exception var1)    {
                receivedText += "\r\n" + "  connection failure";
                Log.i("CyLife Error:", var1.toString());
                var1.printStackTrace();
                setChatText();
            }
        };

        //chat.connect();

        //Following TrustManager and try/catch is ONLY for WWS server, otherwise just use chat.connect() above (presumably)
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                return myTrustedAnchors;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {}

            @Override
            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {}
        } };

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory factory = sslContext.getSocketFactory();
            chat.setSocket(factory.createSocket());
            chat.connectBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }



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
        try {
            chat.send(msg);
        }   catch (WebsocketNotConnectedException e) {
            e.printStackTrace();
            //noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
        }

    }


    //need a way to make background listener
    public String getReceivedStuff()  {
//        BufferedReader in;
//
//        try {
//            in = new BufferedReader(new InputStreamReader(chat.getSocket().getInputStream()));
//
//            while (in.ready()) {
//                receivedText += "echo: " + in.readLine();
//            }
//
//            in.close();
//        }   catch (IOException e)   {
//            e.printStackTrace();
//        }



        return receivedText;
    }


    public void passChatBox(TextView newChatBox)   {
        chatBox = newChatBox;
    }

    public void passActivity(Activity activity) {
        chatActivity = activity;
    }

    public void setChatText()  {
        if(chatBox != null) {

            //necessary for updating visible chat without errors
            chatActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    chatBox.setText(receivedText);
                }
            });

        }
    }

    public WebSocketClient getWebSocket()  {
        return chat;
    }
}
