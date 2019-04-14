package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.controller.*;

class WebSocketSharedObjects {

    public static AccountController getAccountController() {
        return WebSocketAutoWire.getBean(AccountController.class);
    }

    public static BuildingController getBuildingController() {
        return WebSocketAutoWire.getBean(BuildingController.class);
    }

    public static MonsterAttackController getMonsterAttackController() {
        return WebSocketAutoWire.getBean(MonsterAttackController.class);
    }

    public static MonsterController getMonsterController() {
        return WebSocketAutoWire.getBean(MonsterController.class);
    }


    public static MonsterStatController getMonsterStatController() {
        return WebSocketAutoWire.getBean(MonsterStatController.class);

    }

    public static StatsController getStatsController() {
        return WebSocketAutoWire.getBean(StatsController.class);
    }
}
