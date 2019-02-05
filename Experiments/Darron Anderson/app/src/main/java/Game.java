//TODO move this into the proper package
//package com.example.cycondlife;

import android.os.Bundle;

import com.example.cycondlife.Character;

import java.util.Random;
import java.util.ArrayList;

public class Game {
    //TODO make all the rest control from here
    ArrayList<Character> monster_map=new ArrayList<>();
    //how large the generated area should be
    double world_bounds = .2;
    int num_monsters=0;
    //TODO make this based of time
    Random rand=new Random(0);
    Game()
    {
        generate_mMap();
    }
    void generate_mMap()
    {

        while(num_monsters<50);
        {
            generate_monster();
        }
    }
    void generate_monster()
    {
        boolean valid=false;
        while(!valid) {
            //add ames base
            double lat = (rand.nextInt() % 100) / 1000.0 + 42.03 - .05;
            double lon = (rand.nextInt() % 100) / 1000.0 + 93.63 - .05;
            for (int i=0;i<num_monsters;i++)
            {
                if (Math.abs(lat-monster_map.get(i).get_latitude())<.001&&Math.abs(lon-monster_map.get(i).get_longitude())<.001)
                {
                    break;
                }
                if(num_monsters-i==1)
                {
                    monster_map.add(new Character(lat,lon));
                    num_monsters++;
                    valid=true;
                }
            }
        }
    }
}
