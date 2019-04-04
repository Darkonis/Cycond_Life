package com.example.cycondlife;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class Chat extends AppCompatActivity {

    private static Context context;
    private Button sendBut;
    private TextView userMessage;
    private TextView chatText;
    private URI toUse;
    private ChatSender sender;
    //private Toast noConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendBut = findViewById(R.id.sendButton);
        userMessage = findViewById(R.id.userText);
        chatText = findViewById(R.id.chatBox);
        context = getApplicationContext();
        //noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT);

        try {
            toUse = new URI("wss://echo.websocket.org");
        }   catch (URISyntaxException e)    {
            e.printStackTrace();
        }

        sender = new ChatSender();
        sender.connectWebSocket(toUse);
        sender.passChatBox(chatText);

        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }



    public void send()    {
        sender.sendMsg(userMessage.getText().toString());

        //chatText.setText(sender.getReceivedStuff());
    }

    public static Context getChatContext()  {
        return context;
    }


}
