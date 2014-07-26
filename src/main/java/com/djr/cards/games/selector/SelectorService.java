package com.djr.cards.games.selector;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.selector.model.SelectorModel;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/5/14
 * Time: 9:47 PM
 */
public interface SelectorService {
	public List<String> getGameList(String tracking);

	public String getSelectedLandingAction(String tracking, SelectorModel selectorModel);
}
