package com.djr.cards.games.selector.service;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.selector.SelectorDAO;
import com.djr.cards.games.selector.SelectorService;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/5/14
 * Time: 9:48 PM
 */
public class SelectorServiceImpl implements SelectorService {
	@Inject
	private Logger logger;
	@Inject
	private SelectorDAO selectorDao;

	public List<String> getGameList(String tracking) {
		logger.debug("getGameList() - tracking:{}", tracking);
		List<GameSelection> gameSelectionList = null;
		gameSelectionList = selectorDao.findGameSelectionOptions();
        List<String> gameTypes = new ArrayList<String>();
        for (GameSelection gs : gameSelectionList) {
            gameTypes.add(gs.gameType);
        }
		return gameTypes;
	}
}
