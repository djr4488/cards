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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (alias != null ? !alias.equals(user.alias) : user.alias != null) return false;
        if (changePasswordProof != null ? !changePasswordProof.equals(user.changePasswordProof) : user.changePasswordProof != null)
            return false;
        if (createdDate != null ? !createdDate.equals(user.createdDate) : user.createdDate != null) return false;
        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) return false;
        if (hashedPassword != null ? !hashedPassword.equals(user.hashedPassword) : user.hashedPassword != null)
            return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastLoginDate != null ? !lastLoginDate.equals(user.lastLoginDate) : user.lastLoginDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (hashedPassword != null ? hashedPassword.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
        result = 31 * result + (changePasswordProof != null ? changePasswordProof.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
