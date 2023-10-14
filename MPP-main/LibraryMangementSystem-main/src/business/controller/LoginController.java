package business.controller;

import business.usecase.ILogIn;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import business.exception.LoginException;
import dataaccess.repository.child.UserRepository;

public class LoginController extends BaseController implements ILogIn {
	LoginController() {
	}

	public static Auth currentAuth = null;

	@Override
	public Auth login(String id, String password) throws LoginException {
		UserRepository userRepo = new UserRepository(new DataAccessFacade());

		User user = userRepo.login(id,password);
		//User.updateCurrentUser(user);

		//Get Authorization Type (LIBRARIAN, ADMIN, SUPER)
		currentAuth = user.getAuthorization();
		return currentAuth;

	}

}
