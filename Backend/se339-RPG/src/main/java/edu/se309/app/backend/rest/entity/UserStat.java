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

    @Column(name = "monsters_killed", columnDefinition = "UNSIGNED INT(11)")
    private int monstersKilled;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Account account;

    public UserStat() {

    }

    public UserStat(Account account) {
        this.account = account;
    }

    public void copyStats(UserStat newUserStats) {
        setBs(newUserStats.getBs());
        setCriticalThinking(newUserStats.getCriticalThinking());
        setIngenuity(newUserStats.getIngenuity());
        setPresentation(newUserStats.getPresentation());
        setResolve(newUserStats.getResolve());
        setMonstersKilled(newUserStats.getMonstersKilled());
    }

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

    public int getBs() {
        return bs;
    }

    public void setBs(int bs) {
        this.bs = bs;
    }

    public int getCriticalThinking() {
        return criticalThinking;
    }

    public void setCriticalThinking(int criticalThinking) {
        this.criticalThinking = criticalThinking;
    }

    public int getIngenuity() {
        return ingenuity;
    }

    public void setIngenuity(int ingenuity) {
        this.ingenuity = ingenuity;
    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public void setMonstersKilled(int monstersKilled) {
        this.monstersKilled = monstersKilled;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public int getResolve() {
        return resolve;
    }

    public void setResolve(int resolve) {
        this.resolve = resolve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // returns true if newAccount and account are the same
    private boolean sameAccountCheck(Account newAccount) {
        return account == null ? newAccount == null : account.equals(newAccount);
    }

    @Override
    public String toString() {
        return "UserStat [id=" + id + ", bs=" + bs + ", resolve=" + resolve + ", criticalThinking="
                + criticalThinking + ", ingenuity=" + ingenuity + ", presentation=" + presentation + ", monstersKilled="
                + monstersKilled + "]";
    }
}
