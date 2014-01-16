package com.djr.cards.entities.golf;

import com.djr.cards.games.golf.GolfPhase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:40 PM
 */
@Entity
@Table(name="golf")
public class Golf {
    @Id
    private Long id;
    private String gameName;
    private String password;
    private GolfPhase golfPhase;
    private Long outPlayerId;
    private Long playerTurnId;
}
