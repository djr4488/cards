package com.djr.cards.auth.service;

import com.djr.cards.auth.util.HashingUtil;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * User: djr4488
 * Date: 3/14/14
 * Time: 8:06 PM
 */
public class AuthTokenServiceImpl implements AuthTokenService {
	@Inject
	private Logger logger;
	@Inject
	private HashingUtil hashingUtil;

	@Override
	public boolean isValidToken(String token, String trackingId, String userName) {
		logger.debug("isValidToken() - token:{}, trackingId:{}, userName:{}", token, trackingId, userName);
		String generatedToken = hashingUtil.generateHmacHash("<userName>" + userName +
				"</userName><tracking>" + trackingId + "</tracking>");
		return token.equals(generatedToken);
	}
}
