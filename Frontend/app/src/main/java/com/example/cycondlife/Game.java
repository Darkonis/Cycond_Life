//TODO move this into the proper package
package com.example.cycondlife;

import android.os.Bundle;

import com.example.cycondlife.Character;
import com.google.android.gms.maps.GoogleMap;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import edu.se309.app.backend.monsterspawn.*;

public class Game {
    //TODO make all the rest control from here
    public ArrayList<Character> monster_map=new ArrayList<>();
    //how large the generated area should be
    double world_bounds = .2;
    GoogleMap mMap;
    int num_monsters=0;
    //TODO make this based of time
    Random rand=new Random(0);
    Game(GoogleMap mMap)
    {
        this.mMap= mMap;
        generate_mMap();
    }
    void generate_mMap()
    {
    	MonstersController mController = new MonstersController();
    	List<Monsters> monList = mController.findAll();
    	for(int i = 0; i <= monList.size(); i++)
    	{
    		monster_map.add(new Character(monList.get(i).getLat(), monList.get(i).getLon()));
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
