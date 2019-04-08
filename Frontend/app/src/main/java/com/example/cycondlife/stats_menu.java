package com.example.cycondlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

//TODO add stats
public class stats_menu extends AppCompatActivity {
    TextView name;
    //TextView major;
    TextView resolve;
    TextView bs;
    TextView tinkering;
    TextView critical_thinking;
    static Player player= Player.get_instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_menu);
        name = findViewById(R.id.Header);
        //major= findViewById(R.id.Major);
        resolve = findViewById(R.id.resolve);
        bs = findViewById(R.id.bs);
        tinkering= findViewById(R.id.tinkering);
        critical_thinking = findViewById(R.id.critical_thinking);
        update_stats();
    }
    private void update_stats()
    {
        String to_use = "a";
        name.setText(player.getName() + ": " + player.getMajor());
        //major.setText("major: "+ player.getMajor());
        resolve.setText("Resolve: "+ player.getResolve());
        bs.setText("BS: "+player.getBS());
    }

   /* public void setActivityBackgroundColor(int color) {      //Can possibly be used to change background color of activity
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    */
}
