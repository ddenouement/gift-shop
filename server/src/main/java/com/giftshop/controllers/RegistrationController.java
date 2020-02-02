package com.giftshop.controllers;

import com.giftshop.config.TokenProvider;
import com.giftshop.dto.UserLoginRequestDTO;
import com.giftshop.dto.UserRegistrationDTO;
import com.giftshop.models.ConfirmationToken;
import com.giftshop.models.User;
import com.giftshop.services.ConfirmationService;
import com.giftshop.services.UserService;
import com.giftshop.services.interfaces.IConfirmationService;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class RegistrationController {

    private IUserService userService;
    private IConfirmationService confirmationService;

    private TokenProvider jwtTokenProvider;

    @Autowired
    RegistrationController(
            final UserService userService,
            final TokenProvider jwtTokenProvider,
            final ConfirmationService confirmationService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.confirmationService = confirmationService;
    }

    @PostMapping("/user/register")
    public ResponseEntity register(@RequestBody UserRegistrationDTO data, final HttpServletResponse response) {
        if (userService.findUserByEmail(data.email)!=null)
        {
            return badRequest().body("E-mail already exists.");
        }

        User user = new User();
        user.setName(data.firstname);
        user.setSurname(data.surname);
        user.setPatronymic(data.patronymic);
        user.setBirthDate(data.birth_date);
        user.setEmail(data.email);
        user.setPhoneNumber(data.phone_number);
        user.setPassword(data.password);

        userService.insertNewUser(user);
        //ConfirmationToken ct = confirmationService.generateToken(userService.findUserByEmail(data.getEmail()).getUserId());

        //emailService.sendVerificationEmail(data, "Verify your account", confirmationService.getConfirmLink(ct));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/confirm")
    public ResponseEntity<User> confirmUser(@RequestParam("token") String confirmationToken) {
        User user = confirmationService.confirmUser(confirmationToken);
        if (user != null) {
            //emailService.sendGreetingEmail(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /*@PatchMapping("/user/recover")
    public ResponseEntity changePassword(@RequestBody user newUser, @RequestParam("token") String confirmationToken) throws Exception {
        //User user = userService.updatePassword(newUser, confirmationToken);
        //emailService.sendSuccessfulRecoveryEmail(user);
        return ResponseEntity.ok().build();
    }*/

    /*@GetMapping("/user/recover")
    public ResponseEntity requestRecover(@RequestParam("email") String email) throws NotFoundException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new NotFoundException("Cannot find user with email " + email);
        ConfirmationToken ct = confirmationService.generateToken(user.getUserId());
        //emailService.sendRecoveryEmail(user, confirmationService.getRecoveryLink(ct));
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/token/{token}")
    public boolean tokenExists(@PathVariable("token") String token) {
        return confirmationService.exists(token);
    }

    @GetMapping("/address")
    public String address() {
        return System.getenv("HOST_NAME");
    }
}
