package com.example.cycondlife.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        refresh();
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShop)
                {
                    Item i=Item.findByID(Integer.parseInt(id.getText().toString()));
                    if(Player.get_instance().adjustCyBucks(-((int)(i.getCost()*1.2))))
                    {
                        Player.get_instance().addItem((Consumable) i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"not enough funds",Toast.LENGTH_SHORT);
                    }
                }
                else{
                    if(Integer.parseInt(id.getText().toString())<0||Integer.parseInt(id.getText().toString())>=p.getInv().size())
                    {
                        Toast.makeText(getApplicationContext(),"invalid item",Toast.LENGTH_SHORT);
                        return;
                    }
                   Item i= p.removeItem(Integer.parseInt(id.getText().toString()));
                   p.adjustCyBucks((int)(i.getCost()*.8));
                   p.refreshInventory();
                }
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
            submit.setText("Confirm purchase");
            out+="Buying\n";
            ArrayList tmp = Item.itemList;
            for(int i=0;i<tmp.size();i++)
            {
                Consumable c = (Consumable) tmp.get(i);
                out+= "ID: "+ c.getItemID()+ " Name: "+c.getName()+ " Cost: " + (int)(c.getCost()*1.2)+"\n";
            }
        }
        else
        {
            submit.setText("Confirm Sale");
            out+="Selling\n";
            ArrayList tmp = p.getInv();
            for (int i=0;i<tmp.size();i++)
            {
                Consumable c = (Consumable) tmp.get(i);
                out+="ID: "+ i + " Name: "+c.getName()+ " Cost: " + (int)(c.getCost()*.8)+"\n";
            }
        }
        itemList.setText(out);
    }
}
