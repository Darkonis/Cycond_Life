package edu.se309.app.backend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stats")
public class UserStat {

    @Id
    private int id;

    @Column(name = "bs", columnDefinition = "UNSIGNED INT(11)")
    private int bs;

    @Column(name = "resolve", columnDefinition = "UNSIGNED INT(11)")
    private int resolve;

    @Column(name = "critical_thinking", columnDefinition = "UNSIGNED INT(11)")
    private int criticalThinking;

    @Column(name = "ingenuity", columnDefinition = "UNSIGNED INT(11)")
    private int ingenuity;

    @Column(name = "presentation", columnDefinition = "UNSIGNED INT(11)")
    private int presentation;
    
    @Column(name = "cy_bucks")
    private int cyBucks;

    @Column(name = "monsters_killed", columnDefinition = "UNSIGNED INT(11)")
    private int monstersKilled;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Account account;

    /**
     * Default constructor
     */
    public UserStat() {

    }

    /**
     * Constructor for userStat with account
     *
     * @param account account associated with userStat
     */
    public UserStat(Account account) {
        this.account = account;
    }

    /**
     * Copies given userstats to this UserStat
     *
     * @param newUserStats stats to copy
     */
    public void copyStats(UserStat newUserStats) {
        setBs(newUserStats.getBs());
        setCriticalThinking(newUserStats.getCriticalThinking());
        setIngenuity(newUserStats.getIngenuity());
        setPresentation(newUserStats.getPresentation());
        setResolve(newUserStats.getResolve());
        setMonstersKilled(newUserStats.getMonstersKilled());
    }

    /**
     * Checks if given object is equal to this userStat
     *
     * @param obj object to check
     * @return true if equal false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserStat other = (UserStat) obj;
        return id == other.id;
    }

    public Account getAccount() {
        return account;
    }

    /**
     * Set account
     *
     * @param account account
     */
    public void setAccount(Account account) {

        if (sameAccountCheck(account)) {
            return;
        }
        Account oldAccount = this.account;
        if (oldAccount != null) {
            oldAccount.setUserStat(null);
        }
        if (account != null) {
            account.setUserStat(this);
        }
        this.account = account;
    }

    /**
     * Get BS
     *
     * @return BS value
     */
    public int getBs() {
        return bs;
    }

    /**
     * Set BS
     *
     * @param bs BS value
     */
    public void setBs(int bs) {
        this.bs = bs;
    }

    /**
     * Get Critical Thinking
     *
     * @return critical thinking value
     */
    public int getCriticalThinking() {
        return criticalThinking;
    }

    /**
     * Set critical thinking
     *
     * @param criticalThinking critical thinking value
     */
    public void setCriticalThinking(int criticalThinking) {
        this.criticalThinking = criticalThinking;
    }

    /**
     * Get ingenuity
     *
     * @return ingenuity value
     */
    public int getIngenuity() {
        return ingenuity;
    }

    /**
     * Set ingenuity
     *
     * @param ingenuity ingenuity value
     */
    public void setIngenuity(int ingenuity) {
        this.ingenuity = ingenuity;
    }

    /**
     * Get monsters killed
     *
     * @return monsters killed value
     */
    public int getMonstersKilled() {
        return monstersKilled;
    }

    /**
     * Set Monsters Killed
     *
     * @param monstersKilled monsters killed value
     */
    public void setMonstersKilled(int monstersKilled) {
        this.monstersKilled = monstersKilled;
    }

    /**
     * Get presentation
     *
     * @return presentation value
     */
    public int getPresentation() {
        return presentation;
    }

    /**
     * set presentation
     *
     * @param presentation presentation value
     */
    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    /**
     * Get resolve
     *
     * @return resolve value
     */
    public int getResolve() {
        return resolve;
    }

    /**
     * Set resolve
     *
     * @param resolve resolve value
     */
    public void setResolve(int resolve) {
        this.resolve = resolve;
    }

    /**
     * Get Id
     *
     * @return ID
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
     * Hash code for user stat
     *
     * @return Hashcode for user stat
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // returns true if newAccount and account are the same
    private boolean sameAccountCheck(Account newAccount) {
        return account == null ? newAccount == null : account.equals(newAccount);
    }
    
    public int getCyBucks() {
    	return cyBucks;
    }
    
    public void setCyBucks(int cyBucks) {
    	this.cyBucks = cyBucks;
    }

    /**
     * String representation of User Stat
     *
     * @return string representation of User Stat
     */
    @Override
    public String toString() {
        return "UserStat [id=" + id + ", bs=" + bs + ", resolve=" + resolve + ", criticalThinking="
                + criticalThinking + ", ingenuity=" + ingenuity + ", presentation=" + presentation + ", monstersKilled="
                + monstersKilled + "]";
    }
}
