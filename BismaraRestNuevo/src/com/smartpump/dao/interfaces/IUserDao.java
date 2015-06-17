package com.smartpump.dao.interfaces;

import java.util.List;

import com.smartpump.model.User;
import com.smartpump.model.UserState;

public interface IUserDao {

    UserState getUserState(int id);

    List<User> getUsers();

    User getUserById(int id);

    boolean validateUser(String username);

    User validateUser(String username, String password);

    void registerUser(User u);
}
