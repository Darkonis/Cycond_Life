package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.MonsterStat;
import edu.se309.app.backend.rest.service.interfaces.MonsterStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Monster Stat Controller
 */
@RestController
@RequestMapping("/api/monsters/stat")
public class MonsterStatController extends BaseController<MonsterStat, Integer, MonsterStatService> {

    /**
     * Constructor for this Controller
     *
     * @param monsterStatService Service associated with this controller
     */
    @Autowired
    public MonsterStatController(MonsterStatService monsterStatService) {
        super(monsterStatService);
    }
}
