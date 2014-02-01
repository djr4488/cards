package com.djr.cards.data.entities;

import com.djr.cards.auth.AuthModel;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:39 PM
 */
@Entity
@Table(name="cards_users")
@NamedQueries({
        @NamedQuery(name="findUser",
                    query="select user from User user where user.emailAddress = :emailAddress")
})
public class User implements Serializable {

    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="email_address")
    public String emailAddress;
    @Column(name="hashed_password")
    public String hashedPassword;
    @Column(name="user_alias")
    public String alias;
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar createdDate;
    @Column(name="last_login_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar lastLoginDate;
    @Column(name="change_password_proof")
    public String changePasswordProof;


    public User() { }

    public User(AuthModel authModel) {
        emailAddress = authModel.getUserName();
        hashedPassword = authModel.getPassword();
        alias = authModel.getAlias();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
