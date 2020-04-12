package com.giftshop.services.interfaces;

import com.giftshop.dto.UserDTO;
import com.giftshop.dto.UserRegistrationDTO;
import com.giftshop.models.Role;
import com.giftshop.models.User;

import java.util.ArrayList;

public interface IUserService {
    int insertNewUser(User user);
    User findUserById(Integer userId);
    User findUserByEmail(String email );
    ArrayList<UserDTO> getUserInfo(Integer userId);

    Role findUserRoleById(Integer id);
    String userRole(Integer userId);
    Integer deleteUser(String email);



}
