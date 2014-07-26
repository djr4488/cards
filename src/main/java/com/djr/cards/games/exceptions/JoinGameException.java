package com.djr.cards.games.exceptions;

/**
 * @author dannyrucker
 *         Date: 2/18/14
 *         Time: 2:47 PM
 */
public class JoinGameException extends Exception {
	private String message;

	public JoinGameException() {
	}

	public JoinGameException(String message) {
		this.message = message;
	}
}
