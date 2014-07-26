package com.djr.cards.auth.service;

import com.djr.cards.auth.AuthModel;

/**
 * User: djr4488
 * Date: 1/4/14
 * Time: 5:07 PM
 */
public interface AuthService {
	public enum CreateResult {CREATED, USERNAME_TAKEN, OTHER_FAILURE, NOT_IMPLEMENTED}

	;

	public enum ForgotPasswordResult {SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED}

	;

	public enum ChangePasswordResult {SUCCESS, NOT_FOUND, OTHER_FAILURE, NOT_IMPLEMENTED}

	;

	/**
	 * This method takes an AuthModel and based on the information determines if that user can be
	 * created or not.
	 *
	 * @param authModel  AuthModel
	 * @param trackingId String
	 * @return CreateResult This indicates what the result of the attempt was to create the user
	 * CREATE - indicates success
	 * USERNAME_TAKEN - indicates the email address already exists int the system
	 * OTHER_FAILURE - is just a simple way of indicating some other failure occurred
	 */
	public CreateResult createUser(AuthModel authModel, String trackingId);

	/**
	 * This method is for logging into the application
	 *
	 * @param authModel  AuthModel
	 * @param trackingId String
	 * @return Returns a LoginResult which has an enumeration plus if successful also has the User object
	 * for session or other use
	 */
	public LoginResult login(AuthModel authModel, String trackingId);

	/**
	 * This method provides a mechanism for a user to change their password if they forgot it
	 *
	 * @param authModel  AuthModel
	 * @param trackingId String
	 * @return ForgotPasswordResult Indicates the result of contacting the user indicated in the AuthModel
	 */
	public ForgotPasswordResult forgotPassword(AuthModel authModel, String trackingId);

	/**
	 * This method allows the user to change their password based on an "token" sent via email
	 *
	 * @param authModel  AuthModel
	 * @param trackingId String
	 * @return ChangePasswordResult Indicates the result of the change password attempt
	 */
	public ChangePasswordResult changePassword(AuthModel authModel, String trackingId);
}
