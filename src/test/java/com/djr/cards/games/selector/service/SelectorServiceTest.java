package com.djr.cards.games.selector.service;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.selector.SelectorDAO;
import com.djr.cards.games.selector.SelectorService;
import com.djr.cards.games.selector.model.SelectorModel;
import org.junit.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * User: djr4488
 * Date: 2/5/14
 * Time: 9:47 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectorServiceTest extends TestCase {
    @Mock
    private Logger logger;
    @Mock
    private SelectorDAO selectorDao;

    @InjectMocks
    private SelectorService svc = new SelectorServiceImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private GameSelection getGameSelection(String gameName, String landingAction, String gameAction) {
        GameSelection gameSelection = new GameSelection();
        gameSelection.gameAction = gameAction;
        gameSelection.gameType = gameName;
        gameSelection.landingAction = landingAction;
        return gameSelection;
    }

    private List<GameSelection> getGameSelectionList() {
        GameSelection gs1 = getGameSelection("golf", "golfLanding", "golfGameLanding");
        GameSelection gs2 = getGameSelection("pitch", "pitchLanding", "pitchGameLanding");
        List<GameSelection> gameSelections = new ArrayList<GameSelection>();
        gameSelections.add(gs1);
        gameSelections.add(gs2);
        return gameSelections;
    }

    private SelectorModel getSelectorModel() {
        SelectorModel sm = new SelectorModel();
        sm.setSelectedGameType("golf");
        return sm;
    }

    @Test
    public void testGetGameListSuccess() {
        List<GameSelection> gsList = getGameSelectionList();
        String tracking = "testGetGameListSuccess";
        when(selectorDao.findGameSelectionOptions()).thenReturn(gsList);
        List<String> gameNames = svc.getGameList(tracking);
        verify(selectorDao).findGameSelectionOptions();
        assertNotNull(gameNames);
        assertEquals(2, gameNames.size());
    }

    @Test
    public void testGetSelectedLandingAction() {
        SelectorModel sm = getSelectorModel();
        String tracking = "testGetSelectedLandingAction";
        GameSelection gs = getGameSelection("golf", "golfLanding", "golfGameLanding");
        when(selectorDao.findGameSelection(sm.getSelectedGameType())).thenReturn(gs);
        String action = svc.getSelectedLandingAction(tracking, sm);
        verify(selectorDao).findGameSelection(sm.getSelectedGameType());
        assertNotNull(action);
        assertEquals("golfLanding", action);
    }

    @Test
    public void testGetSelectedLandingActionError() {
        SelectorModel sm = getSelectorModel();
        String tracking = "testGetSelectedLandingAction";
        GameSelection gs = getGameSelection("golf", "", "");
        when(selectorDao.findGameSelection(sm.getSelectedGameType())).thenReturn(gs);
        String action = svc.getSelectedLandingAction(tracking, sm);
        verify(selectorDao).findGameSelection(sm.getSelectedGameType());
        assertNotNull(action);
        assertEquals("error", action);
    }
}
