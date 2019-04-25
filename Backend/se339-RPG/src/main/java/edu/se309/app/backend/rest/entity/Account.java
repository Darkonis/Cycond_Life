package edu.se309.app.backend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Class to represent Account
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private UserStat userStat;

    /**
     * Default Constructor
     */
    public Account() {
    }

    /**
     * Constructor for Account
     *
     * @param id          id of account
     * @param username    username of account
     * @param password    password of account
     * @param firstName   first name of account
     * @param lastName    last name of account
     * @param email       email of account
     * @param accountType the type of the account
     */
    public Account(int id, String username, String password, String firstName, String lastName, String email,
                   String accountType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountType = accountType;
    }


    /**
     * Checks if the given object is equal to current account
     * @param obj object to be checked
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
        Account other = (Account) obj;
        return id == other.id && Objects.equals(username, other.username);
    }

    /**
     * Return the account type
     * @return account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Set account type
     * @param accountType account type of the account
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Get the created on date and time
     * @return created on date and time
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Set created on date
     * @param createdOn date of account creation
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Get associated email
     * @return email of account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set associated email
     * @param email email of account
     */
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    /**
     * Get first name of account
     * @return first name associated with account
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name of account
     * @param firstName first name of account
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    /**
     * Get Id of account
     * @return ID of the account
     */
    public int getId() {
        return id;
    }

    /**
     *  Set ID for the account
     * @param id id of the account
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get last name associated with account
     * @return last name associated with this account
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name of this account
     * @param lastName last name of this account
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    /**
     * Get password for this account
     * @return password for this account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password for this account
     * @param password password for this account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get username for this account
     * @return username for this account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username for this account
     * @param username username for this account
     */
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    /**
     * Get userStats
     * @return get stats for this account
     */
    public UserStat getUserStat() {
        return userStat;
    }

    /**
     * Set user stats for this account
     * @param userStat user stats for this account
     */
    public void setUserStat(UserStat userStat) {
        if (userStat.equals(this.userStat)) {
            return;
        }
        this.userStat = userStat;
        userStat.setAccount(this);

    }

    /**
     * Hash code for this account
     * @return hashcode for this account
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    /**
     * Get a String representation of this account
     * @return String representation of this account
     */
    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", createdOn=" + createdOn + ", accountType="
                + accountType + ", userStat=" + userStat + "]";
    }
}
