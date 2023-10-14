package dataaccess;

import java.io.Serializable;

final public class User implements Serializable {

	private static User _current;
	public static User getCurrentUser() {
		return _current;
	}

	public static void updateCurrentUser(User user) {
		_current = user;
	}

	private String id;

	private String password;
	private Auth authorization;

	User(String id, String pass, Auth auth) {
		this.id = id;
		this.password = pass;
		this.authorization = auth;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Auth getAuthorization() {
		return authorization;
	}

	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + authorization.toString() + "]";
	}

}
