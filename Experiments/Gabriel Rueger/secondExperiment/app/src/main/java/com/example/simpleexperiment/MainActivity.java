package com.example.simpleexperiment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Wire up the button

        //..get the button
        Button btn = findViewById(R.id.btnDoStuff);
        //..set what happens when teh user clicks
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is a magic log message!");
                Toast.makeText(getApplicationContext(), "It's doing stuff", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNewWindow = findViewById(R.id.btnNewWindow);

        btnNewWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newWindow.class);
                startActivity(intent);
            }
        });
    }
}
