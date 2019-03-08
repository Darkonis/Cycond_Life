package edu.se309.app.backend.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class UserStat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stats_id")
  private int statsId;
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
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private Account account;

  public UserStat() {}

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
    if (this == obj) { return true; }
    if (obj == null) { return false; }
    if (getClass() != obj.getClass()) { return false; }
    UserStat other = (UserStat) obj;
    return Objects.equals(account, other.getAccount()) && bs == other.getBs()
      && criticalThinking == other.getCriticalThinking() && ingenuity == other.getIngenuity()
      && monstersKilled == other.getMonstersKilled() && presentation == other.getPresentation()
      && resolve == other.getResolve() && statsId == other.getStatsId();
  }

  public Account getAccount() { return account; }

  public int getBs() { return bs; }

  public int getCriticalThinking() { return criticalThinking; }

  public int getIngenuity() { return ingenuity; }

  public int getMonstersKilled() { return monstersKilled; }

  public int getPresentation() { return presentation; }

  public int getResolve() { return resolve; }

  public int getStatsId() { return statsId; }

  @Override
  public int hashCode() {
    return Objects.hash(account, bs, criticalThinking, ingenuity, monstersKilled, presentation,
      resolve, statsId);
  }

  // returns true if newAccount and account are the same
  private boolean sameAccountCheck(Account newAccount) {
    return account == null ? newAccount == null : account.equals(newAccount);
  }

  public void setAccount(Account account) {
    if (sameAccountCheck(account)) { return; }
    Account oldAccount = this.account;
    if (oldAccount != null) { oldAccount.setUserStat(null); }
    if (account != null) { account.setUserStat(this); }
  }

  public void setBs(int bs) { this.bs = bs; }

  public void setCriticalThinking(int criticalThinking) {
    this.criticalThinking = criticalThinking;
  }

  public void setIngenuity(int ingenuity) { this.ingenuity = ingenuity; }

  public void setMonstersKilled(int monstersKilled) { this.monstersKilled = monstersKilled; }

  public void setPresentation(int presentation) { this.presentation = presentation; }

  public void setResolve(int resolve) { this.resolve = resolve; }

  public void setStatsId(int statsId) { this.statsId = statsId; }

  @Override
  public String toString() {
    return "UserStat [statsId=" + statsId + ", bs=" + bs + ", resolve=" + resolve
      + ", criticalThinking="
      + criticalThinking + ", ingenuity=" + ingenuity + ", presentation=" + presentation
      + ", monstersKilled="
      + monstersKilled + "]";
  }
}
