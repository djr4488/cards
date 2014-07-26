package com.djr.cards.auth.dao;

import com.djr.cards.auth.AuthModel;
import com.djr.cards.auth.service.FindUserResult;
import com.djr.cards.data.entities.User;

/**
 * User: djr4488
 * Date: 1/23/14
 * Time: 11:22 PM
 */
public interface UserDAO {
	public FindUserResult findOrCreateUser(AuthModel authModel, String trackingId);

	public User findUser(AuthModel authModel, String trackingId);

	public User updateUser(User user, String trackingId);
}
