package com.djr.cards.games.selector;

import com.djr.cards.data.entities.GameSelection;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/5/14
 * Time: 9:47 PM
 */
public interface SelectorService {
    public List<String> getGameList(String tracking);
}
