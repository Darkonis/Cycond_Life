//TODO move this into the proper package
package com.example.cycondlife;

import android.os.Bundle;

import com.example.cycondlife.Character;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;
import java.util.ArrayList;

public class Game {
    //TODO make all the rest control from here
    public ArrayList<Character> monster_map=new ArrayList<>();
    public Character player=new Character();// replace this later
    //how large the generated area should be
    double world_bounds = .2;
    GoogleMap mMap;
    int num_monsters=0;
    //TODO make this based of time
    Random rand=new Random(0);
    Game(GoogleMap mMap)
    {
        this.mMap= mMap;
    }
    void generate_mMap()
    {

        while(num_monsters<200)
        {
            generate_monster();
        }
    }
    void generate_monster()
    {
        boolean valid=false;
        while(!valid) {

            double lat = (rand.nextInt() % 100) / 1000.0 + 42.03 - .05;
            double lon = (rand.nextInt() % 100) / 1000.0 - 93.63 - .05;
            if(num_monsters==0)
            {
                monster_map.add(new Character(lat,lon));
                num_monsters++;
                valid=true;
                break;
            }
            for (int i=0;i<num_monsters;i++)
            {
                if (Math.abs(lat-monster_map.get(i).get_latitude())<.0001&&Math.abs(lon-monster_map.get(i).get_longitude())<.001)
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
    void display_monsters() {
        for (int i = 0; i < monster_map.size(); i++)
        {
            LatLng t = new LatLng(monster_map.get(i).get_latitude(),monster_map.get(i).get_longitude());
            mMap.addMarker(new MarkerOptions().position(t).title("Monster num" + i));
        }
    }
}
