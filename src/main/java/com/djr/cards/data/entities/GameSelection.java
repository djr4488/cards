package com.djr.cards.data.entities;

import javax.persistence.*;

/**
 * User: djr4488
 * Date: 2/4/14
 * Time: 10:39 PM
 */
@Entity
@Table(name="game_selections")
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
}
