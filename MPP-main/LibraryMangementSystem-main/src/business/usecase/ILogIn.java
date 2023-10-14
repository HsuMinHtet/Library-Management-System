package business.usecase;

import dataaccess.Auth;
import business.exception.LoginException;

public interface ILogIn {
	public Auth login(String id, String password) throws LoginException;
}
