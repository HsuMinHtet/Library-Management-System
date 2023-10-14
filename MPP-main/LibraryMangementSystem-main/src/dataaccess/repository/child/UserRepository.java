package dataaccess.repository.child;

import business.exception.LoginException;
import dataaccess.DataAccess;

import dataaccess.User;
import dataaccess.repository.BaseRepository;

import java.util.HashMap;

public class UserRepository extends BaseRepository {

    public UserRepository(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
    public User login(String id, String password) throws LoginException {

        HashMap<String, User> map = dataAccess.readUserMap();

        if (!map.containsKey(id)) {
            throw new LoginException("Invalid Credentials");
        }

        String userPass = map.get(id).getPassword();
        if (!userPass.equals(password)) {
            throw new LoginException("Invalid Credentials");
        }

        User user = map.get(id);
        return user;


    }
}
