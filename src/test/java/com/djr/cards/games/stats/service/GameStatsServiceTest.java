package com.djr.cards.games.stats.service;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.games.stats.GameStatsService;
import com.djr.cards.games.stats.UserStatsDAO;
import com.djr.cards.games.stats.model.GameStats;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dannyrucker
 *         Date: 2/13/14
 *         Time: 4:53 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class GameStatsServiceTest extends TestCase {
	@Mock
	private Logger logger;
	@Mock
	private UserStatsDAO userStatsDao;
	@InjectMocks
	private GameStatsService golfStatsSvc = new GameStatsServiceImpl();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	private UserStats generateUserStats(String alias, long wins, long totalGames, String emailAddress) {
		UserStats userStats = new UserStats();
		userStats.wins = wins;
		userStats.totalPlayed = totalGames;
		userStats.gameType = "Golf";
		User user = new User();
		user.alias = alias;
		user.emailAddress = emailAddress;
		userStats.user = user;
		return userStats;
	}

	private List<UserStats> generateUserStatsList(int size) {
		List<UserStats> userStatsList = new ArrayList<UserStats>();
		for (int i = 0; i < size; i++) {
			userStatsList.add(generateUserStats("Player-" + i, i, i, "Player.p" + i + "@test.com"));
		}
		return userStatsList;
	}

	@Test
	public void testLoadGolfStatsSuccess() {
		List<UserStats> userStatsList = generateUserStatsList(15);
		User player2 = new User();
		player2.alias = "Player-2";
		player2.emailAddress = "Player.p2@test.com";
		String tracking = "12345";
		UserStats userStats = new UserStats();
		when(userStatsDao.findStatsByUser(any(User.class), any(String.class), any(String.class)))
				.thenReturn(userStats);
		when(userStatsDao.loadStatistics(any(String.class), any(String.class))).thenReturn(userStatsList);
		GameStats gameStats = golfStatsSvc.loadGameStats(tracking, player2, "Game");
		verify(userStatsDao).loadStatistics(any(String.class), any(String.class));
		assertNotNull(gameStats);
		assertEquals(player2.alias, gameStats.getUserStats().getAlias());
		assertEquals(13, gameStats.getUserStats().getRank());
	}

	@Test
	public void testLoadGolfStatsUserStatsMissing() {
		User player2 = new User();
		player2.alias = "Player-2";
		player2.emailAddress = "Player.p2@test.com";
		String tracking = "12345";
		when(userStatsDao.findStatsByUser(any(User.class), any(String.class), any(String.class)))
				.thenReturn(null);
		GameStats gameStats = golfStatsSvc.loadGameStats(tracking, player2, "Game");
		verify(userStatsDao).findStatsByUser(any(User.class), any(String.class), any(String.class));
		assertNull(gameStats);
	}
}
