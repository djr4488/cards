package com.djr.cards.games.services;

import com.djr.cards.audit.AuditService;
import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.game.Game;
import com.djr.cards.games.GameService;
import com.djr.cards.games.models.*;
import com.djr.cards.games.daos.GameDAO;
import com.djr.cards.games.daos.PlayerDAO;
import com.djr.cards.games.exceptions.CreateGameException;
import com.djr.cards.games.exceptions.JoinGameException;
import com.djr.cards.games.exceptions.PlayGameException;
import com.djr.cards.games.selector.SelectorDAO;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:32 AM
 */
public class GameServiceImpl implements GameService {
    @Inject
    private Logger logger;
    @Inject
    private GameDAO gameDao;
    @Inject
    private PlayerDAO playerDao;
    @Inject
    private SelectorDAO selectorDao;
    @Inject
    private AuditService auditService;
    @Inject
    private Integer maxTries = 3;

    @Override
    public CreateGameResult createGame(GameModel gameModel, User user, String tracking) {
        logger.debug("createGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        CreateGameResult result = null;
        try {
            Game game = gameDao.createGame(gameModel, user, tracking);
            auditService.writeAudit(auditService.getAuditLog(tracking, "createGame()", gameModel.toString(),
                    Calendar.getInstance()));
            playerDao.createPlayer(game, user, tracking);
            gameDao.updateGameStatus(game, true, tracking);
            result = new CreateGameResult();
            result.game = game;
            result.actionLanding = selectorDao.findGameSelection(gameModel.getGameType()).gameAction;
        } catch (CreateGameException cgEx) {
            logger.error("createGame() - failed with exception ", cgEx);
            result = new CreateGameResult();
            result.game = null;
            result.actionLanding = "inlineCreate";
        }
        return result;
    }

    private boolean validatePassword(Game game, GameModel gameModel, String tracking) {
        logger.debug("validatePassword() - game:{}, gameModel:{}, tracking:{}", game, gameModel, tracking);
        if (game.gamePassword != null && game.gamePassword.trim().length() >0 &&
                !game.gamePassword.trim().equals(gameModel.getGamePassword().trim())) {
            logger.debug("joinGame() - wrong password");
            return false;
        }
        return true;
    }

    private JoinGameResult joinGameValidation(Game game, GameModel gameModel, User user, String tracking) {
        logger.debug("joinGameValidation() - game:{}, gameModel:{}, user:{}, tracking:{}", game, gameModel,
                user.alias, tracking);
        JoinGameResult joinGameResult = new JoinGameResult();
        if (game == null) {
            joinGameResult.game = null;
            joinGameResult.landingAction = "inlineNotFound";
            return joinGameResult;
        }
        if (!validatePassword(game, gameModel, tracking)) {
            joinGameResult.game = null;
            joinGameResult.landingAction = "inlinePassword";
            return joinGameResult;
        }
        if (playerDao.isUserAPlayer(game, user, tracking)) {
            joinGameResult.game = null;
            joinGameResult.landingAction = "inlineAlreadyJoined";
            return joinGameResult;
        }
        return joinGameResult;
    }

    private JoinGameResult joinRetryValidations(Game game) {
        JoinGameResult joinGameResult = new JoinGameResult();
        if (!game.isWaitingForPlayers) {
            logger.debug("joinGame() - game started before player joined");
            joinGameResult.game = null;
            joinGameResult.landingAction = "inlineStarted";
        } else {
            logger.debug("joinGame() - exceeded maxTries for joining game");
            joinGameResult.game = null;
            joinGameResult.landingAction = "inlineTriesExceeded";
        }
        return joinGameResult;
    }

    @Override
    public JoinGameResult joinGame(GameModel gameModel, User user, String tracking) {
        logger.debug("joinGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        boolean retry = true;
        int tries = 0;
        Game game = gameDao.findGame(gameModel, user, tracking);
        JoinGameResult joinGameResult = joinGameValidation(game, gameModel, user, tracking);
        if (joinGameResult.landingAction != null) {
            return joinGameResult;
        }
        while (retry) {
            game = gameDao.findGame(gameModel, user, tracking);
            if (game.isWaitingForPlayers && tries < maxTries) {
                try {
                    playerDao.createPlayer(game, user, tracking);
                    joinGameResult.game = game;
                    joinGameResult.landingAction = selectorDao.findGameSelection(gameModel.getGameType()).gameAction;
                } catch (OptimisticLockException olEx) {
                    logger.debug("joinGame() - failed to join stale game reference");
                    tries++;
                    continue;
                }
            } else {
                joinGameResult = joinRetryValidations(game);
            }
            retry = false;
        }
        return joinGameResult;
    }

    @Override
    public PlayGameResult playGame(GameModel gameModel, User user, String tracking) throws PlayGameException {
        logger.debug("playGame() - tracking:{}, gameModel:{}, user:{}", tracking, gameModel, user);
        Game game = gameDao.findGame(gameModel, user, tracking);
        PlayGameResult playGameResult = new PlayGameResult();
        if (game == null) {
            playGameResult.landingAction = "inlineGameNotFoundError";
            return playGameResult;
        }
        if (!playerDao.isUserAPlayer(game, user, tracking)) {
            playGameResult.landingAction = "inlineNotInGameError";
            return playGameResult;
        }
        playGameResult.landingAction = selectorDao.findGameSelection(gameModel.getGameType()).gameAction;
        playGameResult.game = game;
        return playGameResult;
    }

    @Override
    public List<PlayerGame> getGamesPlayerIsIn(String gameType, User user, String tracking) {
        logger.debug("getGamesPlayerIsIn() - tracking:{}, gameType:{}, user:{}", tracking, gameType, user.emailAddress);
        List<Game> games = playerDao.findGamesPlayerIsIn(gameType, user, tracking);
        List<PlayerGame> playerGames = new ArrayList<PlayerGame>();
        if (games != null && games.size() >0) {
            for (Game game : games) {
                PlayerGame pg = new PlayerGame();
                pg.gameName = game.gameName;
                pg.gameType = game.gameType;
                playerGames.add(pg);
            }
        }
        return playerGames;
    }
}
