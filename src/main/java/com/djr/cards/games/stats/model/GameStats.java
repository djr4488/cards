package com.djr.cards.games.stats.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/12/14
 * Time: 7:33 PM
 * <p/>
 * TODO: once struts2 removed and test cases corrected clean this class up
 */
@XmlRootElement
public class GameStats {
	private List<PlayerStats> top10;
	private PlayerStats userStats;
	private String gameAction;
	@XmlElement
	private String errorMsg;
	@XmlElement
	private String errorBold;

	public GameStats() {
	}

	public GameStats(List<PlayerStats> top10, PlayerStats userStats) {
		this.top10 = top10;
		this.userStats = userStats;
	}

	@XmlElement
	public List<PlayerStats> getTop10() {
		return top10;
	}

	public void setTop10(List<PlayerStats> top10) {
		this.top10 = top10;
	}

	@XmlElement
	public PlayerStats getUserStats() {
		return userStats;
	}

	public void setUserStats(PlayerStats userStats) {
		this.userStats = userStats;
	}

	@XmlElement
	public String getGameAction() {
		return gameAction;
	}

	public void setGameAction(String gameAction) {
		this.gameAction = gameAction;
	}

	@Override
	public String toString() {
		return "GameStats{" +
				"top10=" + top10 +
				", userStats=" + userStats +
				'}';
	}
}
