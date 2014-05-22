package com.djr.cards.games.stats.service;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.games.daos.GameDAO;
import com.djr.cards.games.daos.PlayerDAO;
import com.djr.cards.games.models.GameModel;
import com.djr.cards.games.stats.GameStatsService;
import com.djr.cards.games.stats.UserStatsDAO;
import com.djr.cards.games.stats.model.GameStats;
import com.djr.cards.games.stats.model.PlayerStats;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/13/14
 * Time: 8:42 AM
 */
public class GameStatsServiceImpl implements GameStatsService {
    @Inject
    private Logger logger;
    @Inject
    private UserStatsDAO userStatsDao;
    @Inject
    private PlayerDAO playerDao;

    private boolean findOrCreateStats(String tracking, User user, String gameType) {
        logger.debug("findOrCreateStats() - tracking:{}, gameType{}", tracking, gameType);
        return userStatsDao.findStatsByUser(user, gameType, tracking) != null;
    }

    private List<UserStats> loadUserStats(String tracking, String gameType) {
        logger.debug("loadUserStats() - tracking:{}, gameType:{}", tracking, gameType);
        List<UserStats> userStats = userStatsDao.loadStatistics(gameType, tracking);
        return userStats;
    }

    private List<PlayerStats> topTenGolfPlayers(String tracking, List<UserStats> userStatsList) {
        logger.debug("topTenGolfPlayers() - tracking:{}, userStatsList size:{}", tracking, userStatsList.size());
        int count = 1;
        List<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
        for (UserStats userStats : userStatsList) {
            PlayerStats playerStats = new PlayerStats(userStats, count);
            playerStatsList.add(playerStats);
            count++;
            if (count > 10) {
                break;
            }
        }
        return playerStatsList;
    }

    private PlayerStats getUserStats(String tracking, User user, List<UserStats> userStatsList) {
        logger.debug("getUserStats() - tracking:{}, user:{}, userStatsListSize:{}", tracking, user,
                userStatsList.size());
        PlayerStats playerStats = null;
        int rank = 1;
        for (UserStats userStats : userStatsList) {
            if(userStats.user.emailAddress.equals(user.emailAddress)) {
                playerStats = new PlayerStats(userStats, rank);
                break;
            }
            rank++;
        }
        return playerStats;
    }

    @Override
    public GameStats loadGameStats(String tracking, User user, String gameType) {
        logger.debug("loadGameStats() - tracking:{}, user:{}, gameType:{}", tracking, user, gameType);
        if (findOrCreateStats(tracking, user, gameType)) {
            List<UserStats> userStats = loadUserStats(tracking, gameType);
            Collections.sort(userStats);
            List<PlayerStats> playerStatsList = topTenGolfPlayers(tracking, userStats);
            PlayerStats playerStats = getUserStats(tracking, user, userStats);
            GameStats gameStats = new GameStats(playerStatsList, playerStats);
            return gameStats;
        }
        logger.debug("loadGameStats() - tracking:{}, gameType:{} - failed to load stats");
        return null;
    }

    @Override
    public List<Game> loadGamesPlayerIsIn(String tracking, User user, GameModel gameModel) {
        logger.debug("loadGamesPlayerIsIn() - gameModel:{}, user:{}, tracking:{}", gameModel, user.alias, tracking);
        List<Game> gameList = playerDao.findGamesPlayerIsIn(gameModel.getGameType(), user, tracking);
        logger.debug("loadGamesPlayerIsIn() - returning {}", gameList);
        return gameList;
    }

}
