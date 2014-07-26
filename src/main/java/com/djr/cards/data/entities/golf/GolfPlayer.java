package com.djr.cards.data.entities.golf;

import com.djr.cards.data.entities.game.Player;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/4/14
 * Time: 4:40 PM
 */
@Entity
@Table(name = "golf_players")
public class GolfPlayer {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "player_id")
	public Player playerId;
}
