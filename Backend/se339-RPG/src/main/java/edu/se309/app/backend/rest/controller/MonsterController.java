package edu.se309.app.backend.rest.controller;

import edu.se309.app.backend.rest.entity.Monster;
import edu.se309.app.backend.rest.service.interfaces.MonsterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Monster Controller
 */
@RestController
@RequestMapping("/api/monsters")
public class MonsterController extends BaseController<Monster, Integer, MonsterService> {

    /**
     * Constructor for this Controller
     *
     * @param monsterService Service associated with this controller
     */
    @Autowired
    public MonsterController(MonsterService monsterService) {
        super(monsterService);
    }

    /**
     * Calls service to generate Monsters
     *
     * @return List of monsters
     */
    @PostMapping("/generate")
    public List<Monster> generateMonster() {
        return getService().generateMonsters();
    }

    /**
     * Return ID of the first monster
     *
     * @return -1 if no monsters else id of first monster
     */
    @ApiOperation(value = "returns first monster's primary key id or -1 if the list is empty")
    @GetMapping("/firstId")
    public int getFirstId() {
        return getService().firstMonsterId();
    }
}
