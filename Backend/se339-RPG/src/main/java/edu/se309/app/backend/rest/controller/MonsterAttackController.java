package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.MonsterAttack;
import edu.se309.app.backend.rest.service.interfaces.MonsterAttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monsters/attack")
public class MonsterAttackController extends BaseController<MonsterAttack, Integer, MonsterAttackService> {

    @Autowired
    public MonsterAttackController(MonsterAttackService monsterAttackService) {
        super(monsterAttackService);
    }
}
