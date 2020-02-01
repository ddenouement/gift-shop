package com.giftshop.services;
import com.giftshop.repository.UserDao;
import com.giftshop.repository.interfaces.IUserDao;
import com.giftshop.models.Role;
import com.giftshop.models.User;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    @Autowired
    UserService(final UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public Role findUserRoleById(int id) {
        return userDao.findUserRoleById(id);
    }

    @Override
    public String userRole(int userId) {
        return findUserRoleById(userId).getRoleName();
    }
}
