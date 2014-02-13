package com.djr.cards.games.golf.stats;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.games.golf.stats.model.GolfStats;
import com.djr.cards.games.golf.stats.service.GolfStatsServiceImpl;
import com.djr.cards.games.stats.UserStatsDAO;
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
 * User: djr4488
 * Date: 2/11/14
 * Time: 8:24 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class GolfStatsServiceTest extends TestCase {
    @Mock
    private Logger logger;
    @Mock
    private UserStatsDAO userStatsDao;
    @InjectMocks
    private GolfStatsService golfStatsSvc = new GolfStatsServiceImpl();

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
        for (int i=0; i<size; i++) {
            userStatsList.add(generateUserStats("Player-"+i, i, i, "Player.p"+i+"@test.com"));
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
        when(userStatsDao.loadStatistics(any(String.class), any(String.class))).thenReturn(userStatsList);
        GolfStats golfStats = golfStatsSvc.loadGolfStats(tracking, player2);
        verify(userStatsDao).loadStatistics(any(String.class), any(String.class));
        assertNotNull(golfStats);
        assertEquals(player2.alias, golfStats.getUserStats().getAlias());
        assertEquals(13, golfStats.getUserStats().getRank());
    }

    @Test
    public void testLoadGolfStatsUserStatsFails() {
        User player2 = new User();
        player2.alias = "Player-2";
        player2.emailAddress = "Player.p2@test.com";
        String tracking = "12345";
        when(userStatsDao.loadStatistics(any(String.class), any(String.class))).thenReturn(null);
        GolfStats golfStats = golfStatsSvc.loadGolfStats(tracking, player2);
        verify(userStatsDao).loadStatistics(any(String.class), any(String.class));
        assertNull(golfStats);
    }
}
