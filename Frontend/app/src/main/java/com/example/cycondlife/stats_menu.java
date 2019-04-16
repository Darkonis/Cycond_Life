package com.example.cycondlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//TODO add stats
public class stats_menu extends AppCompatActivity {
    TextView name;
    //TextView major;
    TextView level;
    TextView resolve;
    TextView bs;
    TextView tinkering;
    //TextView maxTink;
    TextView tinkMult;
    //TextView critical_thinking;
    TextView hitChance;
    TextView sight;
    TextView dodgeChance;
    TextView critChance;
    TextView critMult;
    TextView dmgReduct;
    TextView creativity;

    static Player player= Player.get_instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_menu);
        name = findViewById(R.id.Header);
        level = findViewById(R.id.level);
        resolve = findViewById(R.id.resolve);
        tinkering = findViewById(R.id.tinkering);
        bs = findViewById(R.id.bs);
        tinkMult = findViewById(R.id.tinkMult);
        hitChance = findViewById(R.id.hitChance);
        sight = findViewById(R.id.sight);
        dodgeChance = findViewById(R.id.dodgeChance);
        critChance = findViewById(R.id.critChance);
        critMult = findViewById(R.id.critMult);
        dmgReduct = findViewById(R.id.dmgReduct);
        creativity = findViewById(R.id.creativity);
        //critical_thinking = findViewById(R.id.critical_thinking);
        update_stats();
    }
    private void update_stats()
    {
        String to_use = "a";
        name.setText(player.getName() + ": " + player.getMajor());
        level.setText("Level " + player.getLevel());
        resolve.setText("Resolve: "+ player.getResolve());
        bs.setText("BS: "+player.getBS());
        tinkering.setText("Tinkering: " + player.getTinkeringPoints() + "/" + player.getTinkPointsMax());
        tinkMult.setText("Tinkering Multiplier: " + player.getTinkMult());
        hitChance.setText("Hit Chance: " + player.getHitChance());
        sight.setText("Sight: " + player.getSight());
        dodgeChance.setText("Dodge Chance: " + player.getDodgeChance());
        critChance.setText("Critical Chance: " + player.getCritChance());
        critMult.setText("Critical Multiplier: " + player.getCritMult());
        dmgReduct.setText("Damage Reduction: " + player.getDmgReduct());
        creativity.setText("Creativity: " + player.getCreativity());
    }

   /* public void setActivityBackgroundColor(int color) {      //Can possibly be used to change background color of activity
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    */
}
