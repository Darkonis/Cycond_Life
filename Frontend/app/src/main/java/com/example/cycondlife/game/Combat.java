package com.example.cycondlife.game;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cycondlife.Dice;
import com.example.cycondlife.R;
import com.example.cycondlife.communication.Json_handler;

import java.util.Random;
import java.util.Scanner;

//import Consumable;

public class Combat extends AppCompatActivity {

    //TODO make these private with get methods
    public final static Player player = Player.get_instance();
    public static Character monster;
    static Random rand = new Random(System.currentTimeMillis());
    static Game g;
    Button button_flee;
    Button attack;
    Button tech;
    Button item;
    Button submitItem;
    TextView player_stuff;
    TextView monster_stuff;
    TextView inventory;
    TextView itemID;
    TextView combatLog;

    String combatText;
    int id;

    /**
     * setup the combatants before combat starts
     *
     * @param mnstr enemy
     * @param tmp   game
     */
    public static void set_combatants(Character mnstr, Game tmp) {
        monster = mnstr;
        //player=pyr;
        g = tmp;
    }

    private int do_combat(Character play, Character mon, Context c) {
        //TODO bring up the idea of RNG based on class

        Dice dmg_rng = new Dice("1+1d4");
        if (rand.nextInt() % 100 + 1 <= player.getHitChance()) {
            int dmg = play.BS + dmg_rng.roll();
            if (rand.nextInt() % 100 + 1 <= player.getCritChance()) {
                dmg *= player.getCritMult();
            }


            mon.take_dmg(dmg);
            update_combat_log("You dealt " + dmg + " damage to the enemy!");
            player.getSender().sendMsg("COMBAT ATTACK " + mon.getId());
        }

        if (mon.resolve <= 0) {
            update_combat_log("You defeated the enemy!");
            player.getSender().sendMsg("COMBAT VICTORY " + mon.getId());
            return 1;
        }
        endTurn(c);
        if (play.resolve <= 0) {
            update_combat_log("You have been defeated :(");
            player.getSender().sendMsg("COMBAT DEFEAT " + mon.getId());
            return 2;
        }
        return 0;
    }

    /*
   Do the monsters attack and decrease the time for any consumables
    */
    private void endTurn(Context c) {

        if (rand.nextInt() % 100 + 1 >= player.getDodgeChance()) {
            int dmg = monster.BS;
            dmg *= (1 - player.getDmgReduct());
            player.take_dmg(dmg, c);
            update_combat_log("Your enemy dealt " + dmg + " damage to you...");
            //player.getSender().sendMsg();
        }   else    {
            update_combat_log("Your enemy missed an attack on you...");
        }
        for (int i = 0; i < Player.get_instance().getActives().size(); i++) {
            Log.i("Cycond Info", "Duration:" + Player.get_instance().getActives().get(i).getDuration());
            Consumable t = Player.get_instance().getActives().get(i);
            if (t.getDuration() == 0) {
                player.endItem(t);
                Player.get_instance().getActives().remove(i);
                i--;
                update_combat_log("The effect of your item, " + t.getName() + " has ended");
            } else {
                player.endItem(t);
                t.decreaseDuration();
            }
        }
        Log.i("Cycond Info", "creativity is:" + player.getCreativity());
        player.update_substats();
        Log.i("Cycond Info", "creativity is:" + player.getCreativity());
    }

    /**
     * On the creation of this screen
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        define_elements();
        setup_buttons();
        update_status();
        combatText = "  Combat events:";
        combatLog.setText(combatText);
    }

    private void define_elements() {
        button_flee = findViewById(R.id.run);
        attack = findViewById(R.id.attack);
        player_stuff = findViewById(R.id.health);
        monster_stuff = findViewById(R.id.eHealth);
        inventory = findViewById(R.id.inventoryList);
        item = findViewById(R.id.item);
        itemID = findViewById(R.id.itemID);
        submitItem = findViewById(R.id.useItem);
        combatLog = findViewById(R.id.combatLog);
    }

    /*
    TODO adjust so that online health is updated at the end of combat
     */
    private void setup_buttons() {
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                display_inventory();
                combatLog.setVisibility(View.GONE);
            }
        });
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
                reset();
                int ret = do_combat(player, monster, getApplicationContext());
                update_status();
                if (ret == 1) {
                    Log.i("Cycond Life", "Player has won combat");
                    Json_handler j = new Json_handler(getApplicationContext());

                    player.adjustCyBucks(monster.getGold());
                    finishActivity(1);

                    finish();
                }
                if (ret == 2) {
                    Log.i("Cycond Life", "Player has died");
                    finishActivity(2);
                    finish();
                } else {
                    Log.i("Cycond Life", "Player Health is :" + player.getResolve() + " Enemy hp is: " + monster.getResolve());
                }
            }
        });
        submitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemID.getText().toString().equals("")) {
                    return;
                }
                try{
                    if (player.getInv().size() <= Integer.parseInt(itemID.getText().toString()) || Integer.parseInt(itemID.getText().toString()) < 0) {
                        return;
                    }
                }   catch(NumberFormatException e)  {
                    e.printStackTrace();
                    itemID.setText("");
                    Toast.makeText(getApplicationContext(), "Please enter an integer value", Toast.LENGTH_SHORT);
                    return;
                }


                Item i = player.getInv().get(Integer.parseInt(itemID.getText().toString()));
                // if()
                {
                    ((Consumable) i).use();
                    update_status();
                    player.update_substats();

                }
                player.removeItem(Integer.parseInt(itemID.getText().toString()));
                reset();
                endTurn(getApplicationContext());

            }
        });
    }

    private void reset() {
        inventory.setVisibility(View.GONE);
        inventory.setText("");
        itemID.setVisibility(View.GONE);
        submitItem.setVisibility(View.GONE);
        combatLog.setVisibility(View.VISIBLE);
    }

    private void display_inventory() {
        submitItem.setVisibility(View.VISIBLE);
        itemID.setVisibility(View.VISIBLE);
        inventory.setVisibility(View.VISIBLE);
        String toDisp = "  Your inventory contains:\n";
        for (int i = 0; i < player.getInv().size(); i++) {
            toDisp += "  " + i + " name: " + player.getInv().get(i).name + "\n";
        }
        inventory.setText(toDisp);
    }

    private void update_status() {
        player_stuff.setText("Player Resolve: " + player.getResolve());
        monster_stuff.setText("Enemy Resolve: " + monster.getResolve());
    }

    private void update_combat_log(String text)    {
        combatText += "\r\n  " + text;
        combatLog.setText(combatText);
    }

}