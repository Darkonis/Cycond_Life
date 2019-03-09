package com.example.cycondlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        major= findViewById(R.id.Major);
        resolve = findViewById(R.id.resolve);
        bs = findViewById(R.id.bs);
        tinkering= findViewById(R.id.tinkering);
        critical_thinking = findViewById(R.id.critical_thinking);
        update_stats();
    }
    private void update_stats()
    {
        String to_use = "a";
        name.setText("username: "+ player.getName());
        major.setText("major: "+ player.getMajor());
        resolve.setText("resolve: "+ player.getResolve());
        bs.setText("BS: "+player.getBS());
        tinkering.setText("tinkering: "+ player.getTinkering());
        critical_thinking.setText("critical thinking: "+player.getCritical_thinking());
    }
}
