package com.djr.cards.games;

import com.djr.cards.data.entities.ActiveGames;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.data.entities.WaitingGames;

import java.util.List;

/**
 * User: djr4488
 * Date: 1/16/14
 * Time: 9:04 PM
 */
public class GamesAndStatsServiceImpl implements GamesAndStatsService {
    @Override
    public List<ActiveGames> getActiveGames(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UserStats> getUserStatus(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<WaitingGames> getWaitingGames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
