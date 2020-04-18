package com.giftshop.controllers;


import com.giftshop.dto.UserDTO;
import com.giftshop.models.User;
import com.giftshop.services.UserService;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserController {

    private IUserService userService;

    UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/info/{user-id}")
    public UserDTO getUserInfo(@PathVariable("user-id") Integer u_id){
        return userService.getUserInfo(u_id);
    }
}
