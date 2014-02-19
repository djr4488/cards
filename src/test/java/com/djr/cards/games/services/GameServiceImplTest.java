package com.djr.cards.games.services;

import com.djr.cards.audit.AuditService;
import com.djr.cards.data.entities.GameSelection;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.data.entities.game.Player;
import com.djr.cards.games.GameService;
import com.djr.cards.games.models.CreateGameResult;
import com.djr.cards.games.daos.GameDAO;
import com.djr.cards.games.daos.PlayerDAO;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.models.GameModel;
import com.djr.cards.games.selector.SelectorDAO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 9:59 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest extends TestCase {
    @Mock
    private Logger logger;
    @Mock
    private GameDAO gameDao;
    @Mock
    private PlayerDAO playerDao;
    @Mock
    private SelectorDAO selectorDao;
    @Mock
    private AuditService audit;

    @InjectMocks
    GameService svc = new GameServiceImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private User getUser() {
        User user = new User();
        user.alias = "user";
        return user;
    }

    private Game getGame(User user) {
        Game game = new Game();
        game.gameName = "test";
        game.gamePassword = "test";
        game.gameType = "test";
        game.isWaitingForPlayers = true;
        return game;
    }

    private Player getPlayer(User user, Game game) {
        Player player = new Player();
        player.user = user;
        player.gameId = game;
        return player;
    }

    private GameModel getGameModel() {
        GameModel gm = new GameModel();
        gm.setGameName("test");
        gm.setGamePassword("test");
        gm.setGameType("test");
        return gm;
    }

    private GameSelection getGameSelection() {
        GameSelection gs = new GameSelection();
        gs.gameAction = "testing";
        gs.gameType = "test";
        return gs;
    }

    @Test
    public void testCreateGameSuccess() {
        User user = getUser();
        Game game = getGame(user);
        GameModel gm = getGameModel();
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testCreateGameSuccess";
        try {
            when(gameDao.createGame(gm, user, tracking)).thenReturn(game);
            when(playerDao.createPlayer(game, user, tracking)).thenReturn(player);
            doNothing().when(gameDao).updateGameStatus(game, true, tracking);
            when(selectorDao.findGameSelection(gm.getGameType())).thenReturn(gs);
            CreateGameResult cgResult = svc.createGame(gm, user, tracking);
            verify(gameDao).createGame(gm, user, tracking);
            verify(playerDao).createPlayer(game, user, tracking);
            verify(gameDao).updateGameStatus(game, true, tracking);
            verify(selectorDao).findGameSelection(gm.getGameType());
            assertNotNull(cgResult);
            assertEquals(game, cgResult.game);
            assertEquals("testing", cgResult.actionLanding);
        } catch (CreateGameException cgEx) {
            fail("did not expect exception");
        }
    }
}
