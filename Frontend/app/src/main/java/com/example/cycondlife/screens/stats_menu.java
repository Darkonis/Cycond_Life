package com.example.cycondlife.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.cycondlife.R;
import com.example.cycondlife.game.Player;

//TODO add stats
public class stats_menu extends AppCompatActivity {
    static Player player = Player.get_instance();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_menu);
        name = findViewById(R.id.Header);
        level = findViewById(R.id.level);
        resolve = findViewById(R.id.resolve);
        bs = findViewById(R.id.bs);
        hitChance = findViewById(R.id.hitChance);
        sight = findViewById(R.id.sight);
        dodgeChance = findViewById(R.id.dodgeChance);
        critChance = findViewById(R.id.critChance);
        critMult = findViewById(R.id.critMult);
        creativity = findViewById(R.id.creativity);
        //critical_thinking = findViewById(R.id.critical_thinking);
        update_stats();
    }

    private void update_stats() {
        String to_use = "a";
        name.setText(player.getName() + ": " + player.getMajor());
        level.setText("Level " + player.getLevel());
        resolve.setText("Resolve: " + player.getResolve());
        bs.setText("BS: " + player.getBS());
        //tinkering.setText("Tinkering: " + player.getTinkeringPoints() + "/" + player.getTinkeringPoints());
        //tinkMult.setText("Tinkering Multiplier: " + player.getTinkMult());
        hitChance.setText("Hit Chance: " + player.getHitChance());
        sight.setText("Sight: " + player.getSight());
        dodgeChance.setText("Dodge Chance: " + player.getDodgeChance());
        critChance.setText("Critical Chance: " + player.getCritChance());
        critMult.setText("Critical Multiplier: " + player.getCritMult());
        //dmgReduct.setText("Damage Reduction: " + player.getDmgReduct());
        creativity.setText("Creativity: " + player.getCreativity());
    }

   /* public void setActivityBackgroundColor(int color) {      //Can possibly be used to change background color of activity
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    */
}
