package com.giftshop.services;
import com.giftshop.repository.UserDAO;
import com.giftshop.repository.interfaces.IUserDAO;
import com.giftshop.models.Role;
import com.giftshop.models.User;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserDAO userDAO;

    @Autowired
    UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int insertNewUser(User user) {
        return userDAO.insertNewUser(user);
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
    public Role findUserRoleById(Integer id) {
        return userDAO.findUserRoleById(id);
    }

    @Override
    public String userRole(Integer userId) {
        return findUserRoleById(userId).getRoleName();
    }
}
