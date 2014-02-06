package com.djr.cards.games.selector;

import com.djr.cards.data.entities.GameSelection;

import java.util.List;

/**
 * @author djr4488
 *         Date: 2/5/14
 *         Time: 2:37 PM
 */
public interface SelectorDAO {
	public List<GameSelection> findGameSelectionOptions();
	public GameSelection findGameSelection(String gameType);
}
