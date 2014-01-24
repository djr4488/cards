package com.djr.cards.games;

import com.djr.cards.data.entities.ActiveGames;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.data.entities.WaitingGames;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: djr4488
 * Date: 1/16/14
 * Time: 8:54 PM
 */
public interface GamesAndStatsService {
    public List<ActiveGames> getActiveGames(User user);
    public List<UserStats> getUserStatus(User user);
    public List<WaitingGames> getWaitingGames();
}
