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
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

    private void setupSocket() {
        URI tmp;

        try {
            tmp=new URI("ws://cs309-sd-6.misc.iastate.edu:8080/websocket/"+player.getUsername());
            connectWebSocket(tmp);
        }
        catch(Exception e)
        {
            Log.i("Cycond Error","setup error");
        }
        if(!combatClient.isOpen())
        {
            Log.i("Cy Error","chat didn't open");
        }

    }
    public static void sendMsg(String msg)  {
        try {
            combatClient.send(msg);
        }   catch (WebsocketNotConnectedException e) {
            e.printStackTrace();
            //noConnection = new Toast.makeText(getChatContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
        }

    }
    public Void connectWebSocket(URI dest) {
        //chat = new Socket();

        combatClient = new WebSocketClient(dest) {
            public void onMessage(String var1) {
                Log.i("CyLife Websocket", var1);
            }

            public void onOpen(ServerHandshake var1) {

            }

            public void onClose(int var1, String var2, boolean var3) {

            }

            public void onError(Exception var1) {
                Log.i("CyLife Error:", var1.toString());
                var1.printStackTrace();
            }
        };

        //chat.connect();

        //Following TrustManager and try/catch is ONLY for WWS server, otherwise just use chat.connect() above (presumably)
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                return myTrustedAnchors;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory factory = sslContext.getSocketFactory();
            combatClient.setSocket(factory.createSocket());
            combatClient.connectBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        sendMsg("COMBAT ATTACK " +monster.getId());
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
                sendMsg("SYSTEM: "+ Player.get_instance().getUsername()+" has fled from " + monster.getName());
                sendMsg("COMBAT LOSS "+ monster.getId());
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
                   sendMsg("SYSTEM "+ Player.get_instance().getUsername()+" defeated " + monster.getName());
                   sendMsg("COMBAT VICTORY "+ monster.getId());
                   combatClient.close();
                   finishActivity(1);

                   finish();
               }
               if(ret ==2 )
               {
                   Log.i("Cycond Life","Player has died");
                   sendMsg("SYSTEM "+ Player.get_instance().getUsername()+"has been defeated by" + monster.getName());
                   sendMsg("COMBAT LOSS "+ monster.getId());
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
        Dice dmg_rng = new Dice("1+1d4");
        Random rand =new Random();
        if(rand.nextInt()%100+1<=player.getHitChance()) {
            int dmg = play.BS + dmg_rng.roll();
            if(rand.nextInt()%100+1<=player.getCritChance())
            {
                dmg*=player.getCritMult();
            }
            sendMsg("SYSTEM "+ Player.get_instance().getUsername()+"deals "+ dmg+" to the" + mon.getName());
            mon.take_dmg(dmg);
        }

        if(mon.resolve <=0) return 1;
        if(rand.nextInt()%100+1>=player.getDodgeChance())
        {
            int dmg =mon.BS;
            dmg *=(1-player.getDmgReduct());
            player.take_dmg(dmg,c);
            sendMsg("SYSTEM "+ Player.get_instance().getUsername()+"takes "+ dmg+" from" + mon.getName());
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