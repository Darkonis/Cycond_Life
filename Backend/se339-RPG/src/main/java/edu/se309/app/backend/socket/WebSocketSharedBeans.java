package edu.se309.app.backend.socket;

import edu.se309.app.backend.rest.controller.*;

/**
 * A class used to retrieve beans controlled by Spring to be used by the websocket
 */
class WebSocketSharedBeans {

    /**
     * Returns the AccountController bean controlled by Spring
     *
     * @return AccountController bean
     */
    public static AccountController getAccountController() {
        return WebSocketAutoWire.getBean(AccountController.class);
    }

    /**
     * Returns the BuildingController bean controlled by Spring
     *
     * @return BuildingController bean
     */
    public static BuildingController getBuildingController() {
        return WebSocketAutoWire.getBean(BuildingController.class);
    }

    /**
     * Returns the MonsterAttackController bean controlled by Spring
     *
     * @return MonsterAttackController bean
     */
    public static MonsterAttackController getMonsterAttackController() {
        return WebSocketAutoWire.getBean(MonsterAttackController.class);
    }

    /**
     * Returns the MonsterController bean controlled by Spring
     *
     * @return MonsterController bean
     */
    public static MonsterController getMonsterController() {
        return WebSocketAutoWire.getBean(MonsterController.class);
    }

    /**
     * Returns the MonsterStatController bean controlled by Spring
     *
     * @return MonsterStatController bean
     */
    public static MonsterStatController getMonsterStatController() {
        return WebSocketAutoWire.getBean(MonsterStatController.class);
    }

    /**
     * Returns the StatsController bean controlled by Spring
     *
     * @return StatsController bean
     */
    public static StatsController getStatsController() {
        return WebSocketAutoWire.getBean(StatsController.class);
    }
}
