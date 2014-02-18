package com.djr.cards.games.exceptions;

/**
 * User: djr4488
 * Date: 2/18/14
 * Time: 8:36 AM
 */
public class CreateGameException extends Exception {
    private String message;

    public CreateGameException() { }

    public CreateGameException(String message) {
        this.message = message;
    }
}
