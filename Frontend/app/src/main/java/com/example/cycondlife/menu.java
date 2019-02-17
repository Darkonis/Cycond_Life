package com.example.cycondlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //button object creations
        Button stats = findViewById(R.id.stats);
        Button inventory = findViewById(R.id.inventory);
        Button shop = findViewById(R.id.shop);
        Button friends = findViewById(R.id.friends);
        Button map = findViewById(R.id.map);
        Button dev_menu = findViewById(R.id.dev_menu);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cycond Life", "Attempt to open map from main menu");
                //Toast.makeText(getApplicationContext(), "Opening map...", Toast.LENGTH_SHORT);
                Intent openMap = new Intent(menu.this, MapsActivity.class);
                startActivity(openMap);
            }
        });




    }

    /* public void setActivityBackgroundColor(int color) {      //Can possibly be used to change background color of main menu
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }   */


}
