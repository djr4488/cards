package com.djr.cards.data.entities.game;

import com.djr.cards.data.entities.BooleanToIntegerConverter;
import com.djr.cards.data.entities.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Calendar;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 6:05 PM
 */
@Entity
@Table(name="games")
@NamedQueries({
        @NamedQuery(name="findGameByNameAndType",
                    query="select game from Game game where game.gameName = :gameName and game.gameType = :gameType"),
        @NamedQuery(name="findGamesUserPlaying",
                    query="select game from Game game where game.gameName = :gameName and game.gameType = :gameType and" +
                            " game.user = :user")
})
public class Game {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;
    @Column(name="game_name")
    public String gameName;
    @Column(name="game_password")
    public String gamePassword;
    @Column(name="game_type")
    public String gameType;
    @Column(name="is_waiting")
    public Boolean isWaitingForPlayers;
    @Column(name="last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar lastUpdated;
    @Version
    private Long version;

    public long getId() {
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
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
