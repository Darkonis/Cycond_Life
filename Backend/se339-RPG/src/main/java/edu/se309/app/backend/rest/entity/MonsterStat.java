package edu.se309.app.backend.rest.entity;

import javax.persistence.*;
import java.util.Random;

/**
 * Class representing Monster Stats
 */
@Entity
@Table(name = "monster_stat")
public class MonsterStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "hp")
    private int hp;

    @Column(name = "attack_one")
    private int attackOne;

    @Column(name = "attack_two")
    private int attackTwo;

    @Column(name = "attack_three")
    private int attackThree;

    @Column(name = "attack_four")
    private int attackFour;

    @Column(name = "attack_percent_one")
    private int attackPercentOne;

    @Column(name = "attack_percent_two")
    private int attackPercentTwo;

    @Column(name = "attack_percent_three")
    private int attackPercentThree;

    @Column(name = "attack_percent_four")
    private int attackPercentFour;

    @Column(name = "gold_value")
    private String goldValue;

    @Column(name = "exp_value")
    private String expValue;

    /**
     * Get Attack Four
     *
     * @return attack four
     */
    public int getAttackFour() {
        return attackFour;
    }

    /**
     * Set Attack Four
     *
     * @param attackFour attack four
     */
    public void setAttackFour(int attackFour) {
        this.attackFour = attackFour;
    }

    /**
     * Get Attack One
     *
     * @return attack one
     */
    public int getAttackOne() {
        return attackOne;
    }

    /**
     * Set Attack One
     *
     * @param attackOne attack one
     */
    public void setAttackOne(int attackOne) {
        this.attackOne = attackOne;
    }

    /**
     * Get Attack Percentage Four
     *
     * @return attack percentage four
     */
    public int getAttackPercentFour() {
        return attackPercentFour;
    }

    /**
     * Set Attack Percentage Four
     *
     * @param attackPercentFour attack percentage four
     */
    public void setAttackPercentFour(int attackPercentFour) {
        this.attackPercentFour = attackPercentFour;
    }

    /**
     * Get Attack Percentage One
     *
     * @return attack percentage one
     */
    public int getAttackPercentOne() {
        return attackPercentOne;
    }

    /**
     * Set Attack Percentage One
     *
     * @param attackPercentOne Attack Percentage One
     */
    public void setAttackPercentOne(int attackPercentOne) {
        this.attackPercentOne = attackPercentOne;
    }

    /**
     * Get Attack Percentage Three
     *
     * @return attack percentage three
     */
    public int getAttackPercentThree() {
        return attackPercentThree;
    }

    /**
     * Set Attack Percentage Three
     *
     * @param attackPercentThree attack percentage three
     */
    public void setAttackPercentThree(int attackPercentThree) {
        this.attackPercentThree = attackPercentThree;
    }

    /**
     * Get Attack Percentage Two
     *
     * @return attack percentage two
     */
    public int getAttackPercentTwo() {
        return attackPercentTwo;
    }

    /**
     * Set Attack Percentage Two
     *
     * @param attackPercentTwo attack percentage two
     */
    public void setAttackPercentTwo(int attackPercentTwo) {
        this.attackPercentTwo = attackPercentTwo;
    }

    /**
     * Get Attack Three
     *
     * @return attack three
     */
    public int getAttackThree() {
        return attackThree;
    }

    /**
     * Set Attack Three
     *
     * @param attackThree attack Three
     */
    public void setAttackThree(int attackThree) {
        this.attackThree = attackThree;
    }

    /**
     * Get Attack Two
     *
     * @return attack two
     */
    public int getAttackTwo() {
        return attackTwo;
    }

    /**
     * Set Attack Two
     *
     * @param attackTwo attack two
     */
    public void setAttackTwo(int attackTwo) {
        this.attackTwo = attackTwo;
    }

    /**
     * Get experience value
     *
     * @return experience value
     */
    public String getExpValue() {
        return expValue;
    }

    /**
     * Set experience value
     *
     * @param expValue experience value
     */
    public void setExpValue(String expValue) {
        this.expValue = expValue;
    }

    /**
     * Get gold value
     *
     * @return gold value
     */
    public String getGoldValue() {
        return goldValue;
    }

    /**
     * Set gold value
     *
     * @param goldValue gold value
     */
    public void setGoldValue(String goldValue) {
        this.goldValue = goldValue;
    }

    /**
     * Get hp
     *
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set HP
     *
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Get id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get random attack
     *
     * @return random attack
     */
    public int getRandomAttack() {
        Random rand = new Random();
        int attack = rand.nextInt(100);
        int firstBarrier = this.getAttackPercentOne();
        int secondBarrier = firstBarrier + this.getAttackPercentTwo();
        int thirdBarrier = secondBarrier + this.getAttackPercentThree();
        int fourthBarrier = thirdBarrier + this.getAttackPercentFour();
        if (attack < firstBarrier) {
            return this.getAttackOne();
        } else if (attack < secondBarrier) {
            return this.getAttackTwo();
        } else if (attack < thirdBarrier) {
            return this.getAttackThree();
        } else if (attack < fourthBarrier) {
            return this.getAttackFour();
        }
        return 0;
    }
}
