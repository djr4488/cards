package com.djr.cards.games.selector;

import com.djr.cards.data.entities.GameSelections;

import java.util.List;

/**
 * @author djr4488
 *         Date: 2/5/14
 *         Time: 2:37 PM
 */
public interface SelectorDAO {
	public List<GameSelections> findGameSelectionOptions();
	public GameSelections findGameSelection(String gameType);
}
