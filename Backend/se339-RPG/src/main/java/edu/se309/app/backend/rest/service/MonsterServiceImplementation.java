package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.Monster;
import edu.se309.app.backend.rest.repository.MonsterRepository;
import edu.se309.app.backend.rest.service.interfaces.MonsterService;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Monster Service
 */
@Service
public class MonsterServiceImplementation extends BaseServiceImplementation<Monster, Integer, MonsterRepository>
        implements MonsterService {

    /**
     * Constructor for Monster Service
     * @param monsterRepository Repository associated with this service
     */
    @Autowired
    public MonsterServiceImplementation(MonsterRepository monsterRepository) {
        super(monsterRepository);
    }

    /**
     * Delete all
     */
    @Override
    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

    /**
     * Return the ID of the monster with the lowest ID
     * @return id of lowest monster
     */
    @Override
    @Transactional
    public int firstMonsterId() {
        Optional<Monster> optMonster = getRepository().findFirstByOrderByIdAsc();
        if (!optMonster.isPresent()) {
            return -1;
        } else {
            return optMonster.get().getId();
        }
    }

    /**
     * Randomly generate new monsters
     * @return list of new monsters generated
     */
    @Override
    @Transactional
    public List<Monster> generateMonsters() {
        deleteAll();
        int monstersToGenerate = 50;
        List<Monster> monsters = new ArrayList<>();
        int types[] = {1, 2, 3, 4, 5};
        double baseLatitude[] = {42.0254, 42.0267, 42.0295, 42.0308, 42.0278};
        double baseLongitude[] = {93.6461, 93.6512, 93.6473, 93.6536, 93.6440};
        for (int i = 0; i < monstersToGenerate; i++) {
            for (int j = 0; j < types.length; j++) {
                Monster monster = null;
                Boolean copy = true;
                while (copy) {
                    copy = false;
                    monster = generateRandomMonster(types[j], baseLatitude[j], baseLongitude[j]);
                    for (int k = 0; k < monsters.size(); k++) {
                        if (monster.getLatitude() == monsters.get(k).getLatitude() &&
                                monster.getLongitude() == monsters.get(k).getLongitude()) {
                            copy = true;
                            break;
                        }
                    }
                }
                monsters.add(monster);
                save(monster);
            }
        }
        return findAll();
    }

    private Monster generateRandomMonster(int type, double baseLatitude, double baseLongitude) {
        Random rand = new Random();
        double latitude = rand.nextInt(8) / 1000.0 + baseLatitude;
        double longitude = rand.nextInt(8) / 1000.0 - baseLongitude;
        Monster monster = new Monster();
        monster.setType(type);// sets the type for the monster
        monster.setLatitude(latitude);
        // sets the latitude for the monster
        monster.setLongitude(longitude);// sets the longitude for the monster
        monster.setInCombat(0);//starts monster out of combat
        return monster;
    }

    /**
     * Set status of monster
     * @param id ID of monster
     * @param inCombat Status of monster
     * @return monsters status
     */
    @Override
    @Transactional
    public int markMonster(int id, boolean inCombat) {
        Monster monster = findById(id);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(monster);
        if(inCombat) {
            monster.setInCombat(1);
        } else if(!inCombat) {
            monster.setInCombat(0);
        }
        save(monster);
        return monster.getInCombat();
    }
}
