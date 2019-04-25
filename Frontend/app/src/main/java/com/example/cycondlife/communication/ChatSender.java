package com.example.cycondlife.communication;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

//import org.java_websocket.drafts.Draft_6455;

public class ChatSender {
    //Socket chat;

    String receivedText = "";
    WebSocketClient chat;
    TextView chatBox;
    Activity chatActivity;
    //DataOutputStream dos;
    //PrintWriter pw;
    //Toast noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT);

    /**
     * Connect the websocket to the server
     *
     * @param dest the url to connect to
     * @return return something
     */
    public Void connectWebSocket(URI dest) {
        //chat = new Socket();
        receivedText = "  connecting...";
        setChatText();

        chat = new WebSocketClient(dest) {
            public void onMessage(String var1) {
                receivedText += "\r\n  " + var1;
                //chatBox.setText("");
                setChatText();
                Log.i("CyLife Websocket", var1);
            }

            public void onOpen(ServerHandshake var1) {
                receivedText = "  Welcome to the global chat room!";
                setChatText();
            }

            public void onClose(int var1, String var2, boolean var3) {
                receivedText += "\r\n" + "  disconnected from chat";
                setChatText();
            }

            public void onError(Exception var1) {
                receivedText += "\r\n" + "  connection failure";
                Log.i("CyLife Error:", var1.toString());
                var1.printStackTrace();
                setChatText();
            }
        };
        chat.connect();

        //Following TrustManager and try/catch is ONLY for WWS server, otherwise just use chat.connect() above (presumably)
//        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//            public X509Certificate[] getAcceptedIssuers() {
//                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
//                return myTrustedAnchors;
//            }
//
//            @Override
//            public void checkClientTrusted(X509Certificate[] certs,
//                                           String authType) {}
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] certs,
//                                           String authType) {}
//        } };
//
//        try {
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustAllCerts, new SecureRandom());
//            SSLSocketFactory factory = sslContext.getSocketFactory();
//            chat.setSocket(factory.createSocket());
//            chat.connectBlocking();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


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

    /**
     * send a message to all other users
     *
     * @param msg the message to be sent
     */
    public void sendMsg(String msg) {
        try {
            chat.send(msg);
        } catch (WebsocketNotConnectedException e) {
            e.printStackTrace();
            //noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * return all recieved text
     *
     * @return the text
     */
    //need a way to make background listener
    public String getReceivedStuff() {
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

    /**
     * pass the view for the chat
     *
     * @param newChatBox what is being passed
     */
    public void passChatBox(TextView newChatBox) {
        chatBox = newChatBox;
    }

    /**
     * pass the activity
     *
     * @param activity
     */
    public void passActivity(Activity activity) {
        chatActivity = activity;
    }

    /**
     * update the visible chat
     */
    public void setChatText() {
        if (chatBox != null) {

            //necessary for updating visible chat without errors
            chatActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    chatBox.setText(receivedText);
                }
            });

        }
    }

    /**
     * get the websocket
     *
     * @return the websocket
     */
    public WebSocketClient getWebSocket() {
        return chat;
    }
}
