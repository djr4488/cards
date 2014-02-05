package com.djr.cards.data.dao;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/3/14
 * Time: 10:09 PM
 */
public interface UserStatsDAO {
    public UserStats findStatsByUser(User user, String gameType, String tracking);
    public List<UserStats> loadStatistics(User user, String gameType, String tracking);
}
