package com.example.cycondlife.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;

import android.content.Context;
import android.widget.TextView;

//import Consumable;
import com.example.cycondlife.Dice;


import com.example.cycondlife.R;

import java.util.Random;

public class Combat extends AppCompatActivity {

    Button button_flee;
    Button attack;
    Button tech;
    Button item;
    Button submitItem;

    TextView player_stuff;
    TextView monster_stuff;
    TextView inventory;
    TextView itemID;
    static Random rand =new Random(System.currentTimeMillis());

    //TODO make these private with get methods
    public final static Player player = Player.get_instance();
   public static Character monster;
    static Game g;

    /**
     * On the creation of this screen
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat);
        define_elements();
        setup_buttons();
        update_status();
    }

    /**
     * setup the combatants before combat starts
     * @param mnstr enemy
     * @param tmp game
     */
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
        inventory = findViewById(R.id.inventoryList);
        item= findViewById(R.id.item);
        itemID = findViewById(R.id.itemID);
        submitItem = findViewById(R.id.useItem);
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
        submitItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(itemID.getText().toString().equals(""))
                {
                    return;
                }
                if(player.getInv().size()<=Integer.parseInt(itemID.getText().toString())||Integer.parseInt(itemID.getText().toString())<0)
                {
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
    private void reset()
    {
        inventory.setVisibility(View.GONE);
        inventory.setText("");
        itemID.setVisibility(View.GONE);
        submitItem.setVisibility(View.GONE);
    }
    private void display_inventory()
    {
        submitItem.setVisibility(View.VISIBLE);
        itemID.setVisibility(View.VISIBLE);
        inventory.setVisibility(View.VISIBLE);
        String toDisp="Your inventory contains:\n";
        for(int i=0;i<player.getInv().size();i++)
        {
            toDisp+= i + " name: " +player.getInv().get(i).name+"\n";
        }
        inventory.setText(toDisp);
    }
    private static int do_combat(Character play, Character mon, Context c)
    {
        //TODO bring up the idea of RNG based on class
        Dice dmg_rng = new Dice("1+1d4");
        if(rand.nextInt()%100+1<=player.getHitChance()) {
            int dmg = play.BS + dmg_rng.roll();
            if(rand.nextInt()%100+1<=player.getCritChance())
            {
                dmg*=player.getCritMult();
            }

            mon.take_dmg(dmg);
        }

        if(mon.resolve <=0) return 1;
        endTurn(c);
        if(play.resolve<=0) {

            return 2;
        }
        return 0;
    }
    /*
   Do the monsters attack and decrease the time for any consumables
    */
    private static void endTurn(Context c)
    {

        if(rand.nextInt()%100+1>=player.getDodgeChance())
        {
            int dmg =monster.BS;
            dmg *=(1-player.getDmgReduct());
            player.take_dmg(dmg,c);
        }
        for(int i=0; i<Player.get_instance().getActives().size(); i++)
        {
            Log.i("Cycond Info", "Duration:"+Player.get_instance().getActives().get(i).getDuration());
            Consumable t =Player.get_instance().getActives().get(i);
            if(t.getDuration()==0)
            {
                player.endItem(t);
                Player.get_instance().getActives().remove(i);
                i--;
            }
            else
            {
                player.endItem(t);
                t.decreaseDuration();
            }
        }
        Log.i("Cycond Info","creativity is:"+player.getCreativity());
        player.update_substats();
        Log.i("Cycond Info","creativity is:"+player.getCreativity());
    }

    private void update_status()
    {
        player_stuff.setText("Player Resolve:"+player.getResolve());
        monster_stuff.setText("Enemy Resolve" + monster.getResolve());
    }

}