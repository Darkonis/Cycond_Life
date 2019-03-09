package edu.se309.app.backend.service.interfaces;

import java.util.List;

import edu.se309.app.backend.entity.Monster;

public interface MonsterService extends BaseService<Monster, Integer> {

  void deleteAll();

  int firstMonsterId();

  List<Monster> generateMonsters();
}
