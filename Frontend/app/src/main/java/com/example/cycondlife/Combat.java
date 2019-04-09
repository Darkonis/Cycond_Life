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

import org.java_websocket.client.WebSocketClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;

public class Combat extends AppCompatActivity {

    Button button_flee;
    Button attack;
    Button tech;
    Button item;
    TextView player_stuff;
    TextView monster_stuff;
    static WebSocketClient combatClient;
    final static Player player = Player.get_instance();
    static Character monster;
    static Game g;
    private void setupSocket()
    {
       // SocketAddress s = new So
        if(combatClient.isOpen())return;
        int port = 80;
        SocketAddress sockaddr = new InetSocketAddress("http://cs309-sd-6.misc.iastate.edu", port);
        Proxy p = new Proxy(Proxy.Type.HTTP,sockaddr);
        combatClient.setSocket(new Socket(p));
        combatClient.connect();
    }
    //TODO make a status info where we can display what has happened in combat (a combat log)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        define_elements();
        setup_buttons();
        update_status();
        setupSocket();
        combatClient.send("COMBAT ATTACK");
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
                combatClient.send("SYSTEM:"+ Player.get_instance().getUsername()+"has fled from " + monster.getName());
                combatClient.close();
                finishActivity(3);//flee combat
                finish();
            }
        });
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int ret= do_combat(player,monster,getApplicationContext());
               update_status();
               if(ret ==1)
               {
                   Log.i("Cycond Life","Player has won combat");
                   combatClient.send("SYSTEM:"+ Player.get_instance().getUsername()+"defeated " + monster.getName());
                   combatClient.send("COMBAT VICTORY");
                   combatClient.close();
                   finishActivity(1);

                   finish();
               }
               if(ret ==2 )
               {
                   Log.i("Cycond Life","Player has died");
                   combatClient.send("SYSTEM "+ Player.get_instance().getUsername()+"has been defeated by" + monster.getName());
                   combatClient.send("COMBAT LOSS");
                   combatClient.close();
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
    private static int do_combat(Character play, Character mon, Context c)
    {
        //TODO bring up the idea of RNG based on class
        Dice dmg_rng = new Dice("1+1d4");
        Random rand =new Random();
        if(rand.nextInt()%100+1<=player.getHitChance()) {
            int dmg = play.BS + dmg_rng.roll();
            if(rand.nextInt()%100+1<=player.getCritChance())
            {
                dmg*=player.getCritMult();
            }
            combatClient.send("SYSTEM:"+ Player.get_instance().getUsername()+"deals "+ dmg+" to the" + mon.getName());
            mon.take_dmg(dmg);
        }

        if(mon.resolve <=0) return 1;
        if(rand.nextInt()%100+1>=player.getDodgeChance())
        {
            int dmg =mon.BS;
            dmg *=(1-player.getDmgReduct());
            player.take_dmg(dmg,c);
            combatClient.send("SYSTEM:"+ Player.get_instance().getUsername()+"takes "+ dmg+" from" + mon.getName());
        }

        if(play.resolve<=0) return 2;
        return 0;
    }
    private void update_status()
    {
        player_stuff.setText("Player Resolve:"+player.getResolve());
        monster_stuff.setText("Enemy Resolve" + monster.getResolve());
    }

}