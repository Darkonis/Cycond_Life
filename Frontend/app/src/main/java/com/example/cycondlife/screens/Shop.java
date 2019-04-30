package com.example.cycondlife.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cycondlife.R;
import com.example.cycondlife.game.Consumable;
import com.example.cycondlife.game.Item;
import com.example.cycondlife.game.Player;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {
    private Button state;
    private Button submit;
    private TextView id;
    private TextView itemList;
    private TextView currancy;
    private boolean isShop = true;
    Player p= Player.get_instance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setup();
        setupListeners();
    }
    private void setup()
    {
        submit = findViewById(R.id.submit);
        state = findViewById(R.id.switchState);
        id = findViewById(R.id.itemID);
        currancy = findViewById(R.id.gold);
        itemList = findViewById(R.id.itemList);
    }
    private void setupListeners()
    {
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShop=!isShop;
                refresh();
            }
        });

    }
    public void refresh()
    {
        //TODO add display for if its in Shop or sell mode
        currancy.setText("You have:"+p.getGold()+" cyBucks");
        String out="";
        if(isShop)
        {
            ArrayList tmp = Item.itemList;
            for(int i=0;i<tmp.size();i++)
            {
                Consumable c = (Consumable) tmp.get(i);
                out+= "ID: "+ c.getItemID()+ " Name: "+c.getName()+ "Cost: " + Math.floor(c.getCost()*1.2);
            }
        }
        else
        {
            ArrayList tmp = p.getInv();
            for (int i=0;i<tmp.size();i++)
            {
                Consumable c = (Consumable) tmp.get(i);
                out+="ID: "+ i + " Name: "+c.getName()+ "Cost: " + Math.floor(c.getCost()*.8);
            }
        }
        itemList.setText(out);
    }
}
