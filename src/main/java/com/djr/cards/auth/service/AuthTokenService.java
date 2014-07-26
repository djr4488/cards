package com.djr.cards.auth.service;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 8:00 PM
 */
public interface AuthTokenService {
	boolean isValidToken(String token, String trackingId, String userName);
}
