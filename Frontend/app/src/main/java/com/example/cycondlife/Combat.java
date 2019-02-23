package com.example.cycondlife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Combat extends AppCompatActivity {

    Button button_flee;
    static Character player;
    static Character monster;
    static Game g;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        button_flee = findViewById(R.id.run);
    }
    public static void set_combatants(Character pyr,Character mnstr,Game tmp)
    {
        monster=mnstr;
        player=pyr;
        g=tmp;
    }
    private void setup_buttons()
    {
        button_flee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(2);//flee combat
            }
        });
    }

}