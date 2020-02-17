package com.giftshop.services;

import com.giftshop.models.ConfirmationToken;
import com.giftshop.models.User;
import com.giftshop.repository.ConfirmationTokenDAO;
import com.giftshop.repository.UserDAO;
import com.giftshop.repository.interfaces.IConfirmationTokenDAO;
import com.giftshop.repository.interfaces.IUserDAO;
import com.giftshop.services.interfaces.IConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService implements IConfirmationService {

    private IConfirmationTokenDAO ctDAO;
    private IUserDAO userDAO;

    @Autowired
    ConfirmationService(final ConfirmationTokenDAO ctDAO, final UserDAO userDAO) {
        this.ctDAO = ctDAO;
        this.userDAO = userDAO;
    }

    @Override
    public ConfirmationToken generateToken(Integer userId) {
        ConfirmationToken ct = new ConfirmationToken(userId);
        ctDAO.createToken(ct);
        return ct;
    }

    @Override
    public User confirmUser(String token) {
        ConfirmationToken ct = ctDAO.getByToken(token);
        if (ct != null) {
            User user = userDAO.findUserById(ct.getUserId());
            activateUser(user);
            ctDAO.deleteToken(ct.getTokenId());
            return user;
        }
        return null;
    }

    @Override
    public String getConfirmLink(ConfirmationToken ct) {
        String hostName = System.getenv("HOST_NAME");
        if (hostName == null) hostName = "localhost:4200";
        return "http://" + hostName + "/#/confirm?token=" + ct.getToken();
    }

    @Override
    public String getRecoveryLink(ConfirmationToken ct) {
        String hostName = System.getenv("HOST_NAME");
        if (hostName == null) hostName = "localhost:4200";
        return "http://" + hostName + "/#/password?token=" + ct.getToken();
    }

    @Override
    public boolean exists(String token) {
        return ctDAO.exists(token);
    }

    public void activateUser(User user) {
        user.setIsActivated(true);
        userDAO.updateUser(user);
    }
}
