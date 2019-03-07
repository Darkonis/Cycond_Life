package com.example.cycondlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


public class stats_menu extends AppCompatActivity {
    TextView name;
    TextView major;
    TextView resolve;
    TextView bs;
    TextView tinkering;
    TextView critical_thinking;
    static Player player= Player.get_instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_menu);
        name= findViewById(R.id.name);
        major= findViewById(R.id.Class);
        resolve = findViewById(R.id.resolve);
        bs = findViewById(R.id.bs);
        tinkering= findViewById(R.id.tinkering);
        critical_thinking = findViewById(R.id.critical_thinking);
        update_stats();
    }
    private void update_stats()
    {
        String to_use = "a";
        name.setText("user_name: "+ player.getName());
        to_use = "major: "+ player.getMajor();
        major.setText("major: "+ player.getMajor());
        to_use="resolve: "+ player.getResolve();
        resolve.setText("resolve: "+ player.getResolve());
        to_use="BS: "+player.getBS();
        bs.setText("BS: "+player.getBS());
        to_use="tinkering: "+ player.getTinkering();
        tinkering.setText("tinkering: "+ player.getTinkering());
        to_use="critical thinking: "+player.getCritical_thinking();
        critical_thinking.setText("critical thinking: "+player.getCritical_thinking());
    }
}
