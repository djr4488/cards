package com.djr.cards.data.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:39 PM
 */
@Entity
@Table(name="user_stats")
@NamedQueries({
        @NamedQuery(name="findUserStatsByEmail",
                query="select userStats from UserStats userStats where userStats.user.emailAddress = :emailAddress"),
        @NamedQuery(name="findUserStats",
                query="select userStats from UserStats userStats")
})
public class UserStats implements Comparable<UserStats> {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="id")
    public User user;
    @Column(name="game_type")
    public String gameType;
    @Column(name="wins")
    public Long wins;
    @Column(name="total_played")
    public Long totalPlayed;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, null);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, null);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

    @Override
    public int compareTo(UserStats userStats) {
        if (this.wins > userStats.wins) {
            return 1;
        } else if (this.wins < userStats.wins) {
            return -1;
        }
        return 0;
    }
}
