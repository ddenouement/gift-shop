package com.giftshop.services;
import com.giftshop.dto.UserDTO;
import com.giftshop.repository.UserDAO;
import com.giftshop.repository.interfaces.IUserDAO;
import com.giftshop.models.Role;
import com.giftshop.models.User;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    private IUserDAO userDAO;

    @Autowired
    UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int insertNewUser(User user) {
        return userDAO.insertUser(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userDAO.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public ArrayList<UserDTO> getUserInfo(Integer userId) { return userDAO.getUserInfo(userId); }

    @Override
    public Role findUserRoleById(Integer id) {
        return userDAO.findUserRoleById(id);
    }

    @Override
    public String userRole(Integer userId) {
        return findUserRoleById(userId).getRoleName();
    }

    @Override
    public Integer deleteUser(String email) {
        return userDAO.deleteUser(email);
    }
}
