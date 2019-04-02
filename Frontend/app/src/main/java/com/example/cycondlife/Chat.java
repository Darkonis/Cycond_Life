package com.example.cycondlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;

public class Chat extends AppCompatActivity {

    Button sendBut;
    TextView userMessage;
    URI toUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendBut = findViewById(R.id.sendButton);
        userMessage = findViewById(R.id.userText);

        try {
            toUse = new URI("ws://echo.websocket.org");
        }   catch (URISyntaxException e)    {
            e.printStackTrace();
        }

        sendBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }



    public void send()    {
        ChatSender sender = new ChatSender();
        sender.connectWebSocket(toUse);
        sender.sendMsg(userMessage.getText().toString());
    }


}
