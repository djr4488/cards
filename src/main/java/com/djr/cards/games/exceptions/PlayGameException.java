package com.djr.cards.games.exceptions;

/**
 * @author dannyrucker
 *         Date: 2/18/14
 *         Time: 2:50 PM
 */
public class PlayGameException extends Exception {
    private String message;

    public PlayGameException() { }

    public PlayGameException(String message) {
        this.message = message;
    }
}
