package edu.se309.app.backend.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="accounts")
public class Account {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int accountId;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="account_type", nullable = false)
	private String accountType;
	
	@OneToOne(mappedBy="account", cascade = CascadeType.ALL)
	private UserStat userStat;
	
	public Account() {
		
	}

	public Account(String username, String password, String firstName, String lastName, String email,
			String accountType) {		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public UserStat getUserStat() {
		return userStat;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserStat(UserStat userStat) {
		this.userStat = userStat;
	}

	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", createdOn=" + createdOn
				+ ", accountType=" + accountType + "]";
	}
	
	
	
	
	
}
