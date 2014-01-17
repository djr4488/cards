package com.djr.cards.games;

import com.djr.cards.entities.ActiveGames;
import com.djr.cards.entities.User;
import com.djr.cards.entities.UserStats;
import com.djr.cards.entities.WaitingGames;

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
