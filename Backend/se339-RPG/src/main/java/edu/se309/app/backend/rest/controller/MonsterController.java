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

@RestController
@RequestMapping("/api/monsters")
public class MonsterController extends BaseController<Monster, Integer, MonsterService> {

    @Autowired
    public MonsterController(MonsterService monsterService) {
        super(monsterService);
    }

    @PostMapping("/generate")
    public List<Monster> generateMonster() {
        return getService().generateMonsters();
    }

    @ApiOperation(value = "returns first moster's primary key id or -1 if the list is empty")
    @GetMapping("/firstId")
    public int getFirstId() {
        return getService().firstMonsterId();
    }
}
