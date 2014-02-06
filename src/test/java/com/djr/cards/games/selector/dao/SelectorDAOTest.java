package com.djr.cards.games.selector.dao;

import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.games.selector.SelectorDAO;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/5/14
 * Time: 8:26 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectorDAOTest extends TestCase {
    @Mock
    private Logger logger;
    @Mock
    private EntityManager em;
    @Mock
    private TypedQuery<GameSelection> query;

    @InjectMocks
    private SelectorDAO selectorDao = new SelectorDAOImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     public void testFindSelectionOptionsSuccess() {
        List<GameSelection> gameSelectionList = new ArrayList<GameSelection>();
        when(em.createNamedQuery("findAllGameOptions", GameSelection.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(gameSelectionList);
        assertNotNull(selectorDao.findGameSelectionOptions());
    }

    @Test
    public void testFindSelectionOptionsFails() {
        List<GameSelection> gameSelectionList = new ArrayList<GameSelection>();
        when(em.createNamedQuery("findAllGameOptions", GameSelection.class)).thenReturn(query);
        when(query.getResultList()).thenThrow(new NoResultException("No games to choose from"));
        assertNull(selectorDao.findGameSelectionOptions());
    }

    @Test
    public void testFindGameSelectionSuccess() {
        GameSelection gameSelection = new GameSelection();
        when(em.createNamedQuery("findGameSelectionByGameType", GameSelection.class)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(gameSelection);
        assertNotNull(selectorDao.findGameSelection("golf"));
    }

    @Test
    public void testFindGameSelectionFails() {
        GameSelection gameSelection = new GameSelection();
        when(em.createNamedQuery("findGameSelectionByGameType", GameSelection.class)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException("No game type found"));
        assertNull(selectorDao.findGameSelection("golf"));
    }
}
