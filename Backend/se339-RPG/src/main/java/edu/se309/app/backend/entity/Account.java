package edu.se309.app.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "accounts")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
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

    public Account() {
    }

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

    public int getId() {
        return id;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setUserStat(UserStat userStat) {
        if (userStat.equals(this.userStat)) {
            return;
        }
        this.userStat = userStat;
        userStat.setAccount(this);

    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", createdOn=" + createdOn + ", accountType="
                + accountType + ", userStat=" + userStat + "]";
    }
}
