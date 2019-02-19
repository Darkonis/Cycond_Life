package edu.se309.app.backend.entity;

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
@Table(name="stats")
public class UserStat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stats_id")
	private int statsId;
	
	@Column(name="bs",columnDefinition = "UNSIGNED INT(11)")
	private int bs;
	
	@Column(name="resolve",columnDefinition = "UNSIGNED INT(11)")
	private int resolve;
	
	@Column(name="critical_thinking",columnDefinition = "UNSIGNED INT(11)")
	private int criticalThinking;
	
	@Column(name="ingenuity",columnDefinition = "UNSIGNED INT(11)")
	private int ingenuity;
	
	@Column(name="presentation",columnDefinition = "UNSIGNED INT(11)")
	private int presentation;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	public UserStat() {
		
	}
		
	public UserStat(int bs, int resolve, int criticalThinking, int ingenuity, int presentation) {
		this.bs = bs;
		this.resolve = resolve;
		this.criticalThinking = criticalThinking;
		this.ingenuity = ingenuity;
		this.presentation = presentation;		
	}

	public Account getAccount() {
		return account;
	}

	public int getBs() {
		return bs;
	}

	public int getCriticalThinking() {
		return criticalThinking;
	}

	public int getIngenuity() {
		return ingenuity;
	}

	public int getPresentation() {
		return presentation;
	}

	public int getResolve() {
		return resolve;
	}

	public int getStatsId() {
		return statsId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	public void setBs(int bs) {
		this.bs = bs;
	}

	public void setCriticalThinking(int criticalThinking) {
		this.criticalThinking = criticalThinking;
	}

	public void setIngenuity(int ingenuity) {
		this.ingenuity = ingenuity;
	}

	public void setPresentation(int presentation) {
		this.presentation = presentation;
	}

	public void setResolve(int resolve) {
		this.resolve = resolve;
	}

	public void setStatsId(int statsId) {
		this.statsId = statsId;
	}

	@Override
	public String toString() {
		return "UserStat [statsId=" + statsId + ", bs=" + bs + ", resolve=" + resolve
				+ ", criticalThinking=" + criticalThinking + ", ingenuity=" + ingenuity + ", presentation="
				+ presentation + "]";
	}


}
