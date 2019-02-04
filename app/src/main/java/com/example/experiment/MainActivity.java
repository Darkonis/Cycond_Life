package com.example.experiment;

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

        // Make the button do stuff
        // ...get the button
        Button btn = findViewById(R.id.btnDoStuff);
        Button menuBtn = findViewById(R.id.btnMenu);
        // ...set what happens when the user taps the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "This is just a log message!");
                Toast.makeText(getApplicationContext(), "It's working!", Toast.LENGTH_SHORT)
                    .show();
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuExperiment.class);
                startActivity(intent);
            }
        });
    }
}
