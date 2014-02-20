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
import com.djr.cards.games.models.JoinGameResult;
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

import javax.enterprise.inject.Produces;
import javax.persistence.OptimisticLockException;

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

    @Test
    public void testCreateGameException() {
        User user = getUser();
        Game game = getGame(user);
        GameModel gm = getGameModel();
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testCreateGameException";
        try {
            when(gameDao.createGame(gm, user, tracking)).thenThrow(new CreateGameException("already exists"));
            CreateGameResult cgResult = svc.createGame(gm, user, tracking);
            verify(gameDao).createGame(gm, user, tracking);
            assertNotNull(cgResult);
            assertNull(cgResult.game);
            assertEquals("inlineCreate", cgResult.actionLanding);
        } catch (CreateGameException cgEx) {
            fail("did not expect exception");
        }
    }

    @Test
    public void testJoinGameSuccess() {
        User user = getUser();
        Game game = getGame(user);
        GameModel gm = getGameModel();
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testJoinGameSuccess";
        try {
            when(gameDao.findGame(gm, user, tracking)).thenReturn(game);
            when(playerDao.createPlayer(game, user, tracking)).thenReturn(player);
            when(selectorDao.findGameSelection(gm.getGameType())).thenReturn(gs);
            JoinGameResult jgResult = svc.joinGame(gm, user, tracking);
            verify(gameDao, times(2)).findGame(gm, user, tracking);
            verify(playerDao).createPlayer(game, user, tracking);
            verify(selectorDao).findGameSelection(gm.getGameType());
            assertNotNull(jgResult);
            assertEquals(jgResult.game, game);
            assertEquals(jgResult.landingAction, gs.gameAction);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("did not expect exception here");
        }
    }

    @Test
    public void testJoinGameWrongPassword() {
        User user = getUser();
        Game game = getGame(user);
        GameModel gm = getGameModel();
        gm.setGamePassword("testing");
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testJoinGameWrongPassword";
        try {
            when(gameDao.findGame(gm, user, tracking)).thenReturn(game);
            JoinGameResult jgResult = svc.joinGame(gm, user, tracking);
            verify(gameDao).findGame(gm, user, tracking);
            assertNotNull(jgResult);
            assertEquals(jgResult.landingAction, "inlinePassword");
            assertNull(jgResult.game);
        } catch (Exception ex) {
            fail("did not expect exception here");
        }
    }

    @Test
    public void testJoinGameStarted() {
        User user = getUser();
        Game game = getGame(user);
        game.isWaitingForPlayers = false;
        GameModel gm = getGameModel();
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testJoinGameStarted";
        try {
            when(gameDao.findGame(gm, user, tracking)).thenReturn(game);
            JoinGameResult jgResult = svc.joinGame(gm, user, tracking);
            verify(gameDao, times(2)).findGame(gm, user, tracking);
            assertNotNull(jgResult);
            assertNull(jgResult.game);
            assertEquals(jgResult.landingAction, "inlineStarted");
        } catch (Exception ex) {
            fail("did not expect exception here");
        }
    }

    @Test
    public void testJoinGameTriesExceeded() {
        User user = getUser();
        Game game = getGame(user);
        GameModel gm = getGameModel();
        Player player = getPlayer(user, game);
        GameSelection gs = getGameSelection();
        String tracking = "testJoinGameStarted";
        try {
            when(gameDao.findGame(gm, user, tracking)).thenReturn(game);
            when(playerDao.createPlayer(game, user, tracking)).thenThrow(new OptimisticLockException("version mismatch"));
            JoinGameResult jgResult = svc.joinGame(gm, user, tracking);
            verify(gameDao, times(5)).findGame(gm, user, tracking);
            verify(playerDao, times(3)).createPlayer(game, user, tracking);
            assertNotNull(jgResult);
            assertNull(jgResult.game);
            assertEquals(jgResult.landingAction, "inlineTriesExceeded");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            fail("did not expect exception here");
        }
    }
}
