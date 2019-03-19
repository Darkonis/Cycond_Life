package Update;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testCase {
	public static void main(String[] args) throws MalformedURLException, IOException {
	    int monstersToGenerate = 10;
	    List<Monster> monsters = new ArrayList<>();
	    int types[] = {1, 2, 3, 4, 5};
	    double baseLatitude[] = {42.0254, 42.0267, 42.0295, 42.0308, 42.0278};
	    double baseLongitude[] = {93.6461, 93.6512, 93.6473, 93.6536, 93.6440};
	    for (int i = 0; i < monstersToGenerate; i++) {
	      for (int j = 0; j < types.length; j++) {
	    	Monster monster = null;
	    	Boolean copy = true;
	    	while(copy)
	    	{
	    		copy = false;
	    		 monster = generateRandomMonster(types[j], baseLatitude[j], baseLongitude[j]);
	    		 for(int k = 0; k < monsters.size(); k++)
	    		 {
	    			 if(monster.getLatitude() == monsters.get(k).getLatitude() &&  
	    					 monster.getLongitude() == monsters.get(k).getLongitude())
	    			 {
	    				 copy = true;
	    				 break;
	    			 }
	    		 }
	    	}
	    	monsters.add(monster);
	     }
	    }
	}
	        private static Monster generateRandomMonster(int type, double baseLatitude, double baseLongitude) {
	            Random rand = new Random();
	            double latitude = rand.nextInt(3) / 1000.0 + baseLatitude;
	            double longitude = rand.nextInt(3) / 1000.0 - baseLongitude;
	            Monster monster = new Monster();
	            monster.setType(type);// sets the type for the monster
	            monster.setLatitude(latitude);
	            // sets the latitude for the monster
	            monster.setLongitude(longitude);// sets the longitude for the monster
	            return monster;
	        }
}
