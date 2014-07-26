package com.djr.cards.data.entities.golf;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.golf.GolfPhase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:40 PM
 */
@Entity
@Table(name = "golf")
public class Golf {
	@Id
	private Long id;
	public String gameName;
	public String password;
	public GolfPhase golfPhase;
	public Long outPlayerId;
	public Long playerTurnId;
	@ManyToOne
	@JoinColumn(name = "game_type_id")
	public GameSelection gameTypeId;
}
