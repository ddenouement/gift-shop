package com.giftshop.repository.interfaces;

import com.giftshop.models.Role;
import com.giftshop.models.User;

public interface IUserDAO {
    boolean isEmailUsed(String email);
    User findUserByEmail(String email);
    Role findUserRoleById(int id);
    User findUserById(int userId);
    int insertNewUser(User user);
    void updateExistingUser(User user);
}
