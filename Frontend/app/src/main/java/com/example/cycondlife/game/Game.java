//TODO move this into the proper package
package com.example.cycondlife.game;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;
//import edu.se309.app.backend.monsterspawn.*;

public class Game {
    public static final Player player = Player.get_instance();
    public static ArrayList<Character> monster_map = new ArrayList<>();
    public static int num_monsters = 0;
    public GoogleMap mMap;
    //how large the generated area should be
    double world_bounds = .2;
    Random rand = new Random(System.currentTimeMillis());

    public Game(GoogleMap mMap) {
        this.mMap = mMap;
    }

    /**
     * add a monster to the list
     *
     * @param c the monster to be added
     */
    public static void add_monster(Character c) {
        monster_map.add(c);
    }

    /**
     * change the players resolve
     *
     * @param hp the hp
     * @param c  the context
     */
    public static void change_player_hp(int hp, Context c) {
        player.setResolve(hp);
        player.update_stat(hp, "resolve", c);
    }

    @Deprecated
    void generate_mMap() {

        while (num_monsters < 200) {
            generate_monster();
        }
    }

    @Deprecated
    void generate_monster() {
        boolean valid = false;
        while (!valid) {

            double lat = (rand.nextInt() % 100) / 1000.0 + 42.03 - .05;
            double lon = (rand.nextInt() % 100) / 1000.0 - 93.63 - .05;
            if (num_monsters == 0) {
                monster_map.add(new Character(lat, lon));
                num_monsters++;
                valid = true;
                break;
            }
            for (int i = 0; i < num_monsters; i++) {
                if (Math.abs(lat - monster_map.get(i).get_latitude()) < .0001 && Math.abs(lon - monster_map.get(i).get_longitude()) < .001) {
                    break;
                }
                if (num_monsters - i == 1) {
                    monster_map.add(new Character(lat, lon));
                    num_monsters++;
                    valid = true;
                }
            }

        }
    }

    void display_monsters() {
        for (int i = 0; i < monster_map.size(); i++) {
            LatLng t = new LatLng(monster_map.get(i).get_latitude(), monster_map.get(i).get_longitude());
            mMap.addMarker(new MarkerOptions().position(t).title("Monster num" + i));
        }
    }
}
