package com.djr.cards.data.entities;

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
        if (this == o) return true;
        if (!(o instanceof UserStats)) return false;

        UserStats userStats = (UserStats) o;

        if (gameType != null ? !gameType.equals(userStats.gameType) : userStats.gameType != null) return false;
        if (id != null ? !id.equals(userStats.id) : userStats.id != null) return false;
        if (totalPlayed != null ? !totalPlayed.equals(userStats.totalPlayed) : userStats.totalPlayed != null)
            return false;
        if (user != null ? !user.equals(userStats.user) : userStats.user != null) return false;
        if (wins != null ? !wins.equals(userStats.wins) : userStats.wins != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (gameType != null ? gameType.hashCode() : 0);
        result = 31 * result + (wins != null ? wins.hashCode() : 0);
        result = 31 * result + (totalPlayed != null ? totalPlayed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "id=" + id +
                ", user=" + user +
                ", gameType='" + gameType + '\'' +
                ", wins=" + wins +
                ", totalPlayed=" + totalPlayed +
                '}';
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
