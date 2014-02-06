package com.djr.cards.data.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * User: djr4488
 * Date: 2/4/14
 * Time: 10:39 PM
 */
@Entity
@Table(name="game_selections")
@NamedQueries({
        @NamedQuery(name="findAllGameOptions",
                    query="select gameSelections from GameSelection gameSelections"),
        @NamedQuery(name="findGameSelectionByGameType",
                    query="select gameSelection from GameSelection gameSelection where" +
                            " gameSelection.gameType = :gameType")
})
public class GameSelection {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="game_type")
    public String gameType;
    @Column(name="game_description")
    public String gameDescription;
    @Column(name="landing_action")
    public String landingAction;

    public int getId() {
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
}
