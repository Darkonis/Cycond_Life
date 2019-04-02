package com.example.cycondlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Chat extends AppCompatActivity {

    Button sendBut = findViewById(R.id.send);
    TextView userMessage = findViewById(R.id.userText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void send(View v)    {
        ChatSender sender = new ChatSender();
        sender.execute(userMessage.getText().toString());
    }


}
