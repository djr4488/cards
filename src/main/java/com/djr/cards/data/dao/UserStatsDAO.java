package com.djr.cards.data.dao;

import com.djr.cards.data.entities.UserStats;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/3/14
 * Time: 10:09 PM
 */
public interface UserStatsDAO {
    public UserStats findStatsByEmailAddress(String emailAddress);
    public List<UserStats> loadStatistics(String emailAddress);
}
