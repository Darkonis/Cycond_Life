package com.example.cycondlife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Combat extends AppCompatActivity {

    Button button_flee;
    Button attack;
    Button tech;
    Button item;
    TextView player_stuff;
    TextView monster_stuff;
    final static Player player = Player.get_instance();
    static Character monster;
    static Game g;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        define_elements();
        setup_buttons();
        update_status();
    }
    public static void set_combatants(Character mnstr,Game tmp)
    {
        monster=mnstr;
        //player=pyr;
        g=tmp;
    }
    private void define_elements()
    {
        button_flee = findViewById(R.id.run);
        attack = findViewById(R.id.attack);
        player_stuff=findViewById(R.id.health);
        monster_stuff=findViewById(R.id.eHealth);
    }
    /*
    TODO adjust so that online health is updated at the end of combat
     */
    private void setup_buttons()
    {
        button_flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(3);//flee combat
                finish();
            }
        });
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int ret= Character.do_combat(player,monster,getApplicationContext());
               update_status();
               if(ret ==1)
               {
                   Log.i("Cycond Life","Player has won combat");
                   finishActivity(1);
                   finish();
               }
               if(ret ==2 )
               {
                   Log.i("Cycond Life","Player has died");
                   finishActivity(2);
                   finish();
               }
               else
               {
                   Log.i("Cycond Life","Player Health is :" +player.getResolve() + " Enemy hp is: " + monster.getResolve());
               }
            }
        });
    }
    private void update_status()
    {
        player_stuff.setText("Player Resolve:"+player.getResolve());
        monster_stuff.setText("Enemy Resolve" + monster.getResolve());
    }

}