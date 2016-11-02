package com.djr.cards.games.golf.services;

import com.djr.cards.data.entities.User;
import com.djr.cards.games.golf.GolfService;
import com.djr.cards.games.golf.model.*;

import java.util.List;

/**
 * User: djr4488
 * Date: 2/20/14
 * Time: 6:37 PM
 */
public class GolfServiceImpl implements GolfService {
    public boolean isAllPlayersJoined(String tracking, User user, String gameName) {
        //when player joins is player creator?
        //  no
        //    then add player to game
        //    is this last player needed to join?
        //      yes
        //        then change state to TURN_THREE else continuing in waiting
        //  yes
        //    return to game control screen?
        return false;
    }

    /**
     * Allow player to create game
     * Validate that game name doesn't already exist for game type golf
     * If not; create game
     * If so; return error
     */
    public CreateGolfGameResponse createGolfGame(String tracking, User user, String gameName, List<String> emailInviteList) {
        return null;
    }

    /**
     * Starts golf round by gameName
     * Randomly selects 15 cards for each player
     * Randomly determines if player gets bonus
     * Resets the last round flag and marker
     * Emails each player they now get to Turn over 3 cards
     *
     * @param tracking
     * @param gameName
     * @return
     */
    public GolfStartResponse doStartRound(String tracking, String gameName) {
        return null;
    }

    /**
     * Allows player to turn over three cards to start round
     * Once all players have turned over three cards to start; then informs the games "opening" player
     * to play their turn
     * @param tracking
     * @param user
     * @param gameName
     * @return
     */
    public GolfTurnThreeResponse doTurnThree(String tracking, User user, String gameName) {
        return null;
    }

    /**
     * Allows player to decide if wants to draw card or play prior players discarded card
     * Response should include the discarded card that player can see
     * @param tracking
     * @param user
     * @param gameName
     * @return
     */
    public GolfYourTurnResponse doYourTurn(String tracking, User user, String gameName) {
        return null;
    }

    /**
     * If player decides to draw a card this is the card that is drawn player still needs to decide if
     * wants to play or discard the card
     * @param tracking
     * @param user
     * @param gameName
     * @return
     */
    public GolfDrawCardResponse doDrawCard(String tracking, User user, String gameName) {
        return null;
    }

    /**
     * Player can play a card or discard a card
     * This method should also check if this card is played, did it cause the player to have all 15 cards face up?
     * If so, this triggers, the "last round" with the marker set on this player(if last round is not already set)
     * @param tracking
     * @param user
     * @param gameName
     * @param cardLocation
     * @param discard
     * @return
     */
    public GolfPlayCardResponse doPlayCard(String tracking, User user, String gameName, String cardLocation, boolean discard) {
        return null;
    }
}
