package com.giftshop.services.interfaces;

import com.giftshop.dto.UserRegistrationDTO;
import com.giftshop.models.Role;
import com.giftshop.models.User;

public interface IUserService {
    int insertNewUser(User user);
    User findUserById(int userId);
    User findUserByEmail(String email );

    Role findUserRoleById(int id);
    String userRole(int userId);



}
