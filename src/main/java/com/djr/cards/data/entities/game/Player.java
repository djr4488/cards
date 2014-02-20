package com.djr.cards.data.entities.game;

import com.djr.cards.data.entities.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 6:10 PM
 */
@Entity
@Table(name="players")
@NamedQueries ({
        @NamedQuery(name="findUserAsPlayer",
                    query="select player from Player player where player.user = :user and player.gameId = :game")
})
public class Player {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;
    @OneToOne
    @JoinColumn(name="game_id")
    public Game gameId;

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
        return super.toString();
    }
}
