package edu.se309.app.backend.rest.service.interfaces;

import edu.se309.app.backend.rest.entity.Monster;

import java.util.List;

public interface MonsterService extends BaseService<Monster, Integer> {

    void deleteAll();

    int firstMonsterId();

    List<Monster> generateMonsters();
    
    int markMonster(int id, boolean inCombat);
}