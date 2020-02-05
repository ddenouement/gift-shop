package com.giftshop.controllers;

import com.giftshop.models.User;
import com.giftshop.services.ConfirmationService;
import com.giftshop.services.UserService;
import com.giftshop.services.interfaces.IConfirmationService;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private IUserService userService;
    private IConfirmationService confirmationService;

    @Autowired
    RegistrationController(
            final UserService userService,
            final ConfirmationService confirmationService) {
        this.userService = userService;
        this.confirmationService = confirmationService;
    }

    @PostMapping("/user")
    public ResponseEntity register(@RequestBody User user, final HttpServletResponse response) {
        if (userService.findUserByEmail(user.getEmail())!=null)
        {
            return badRequest().body("E-mail already exists.");
        }

        if  (userService.insertNewUser(user)==0) return badRequest().body("Error");
        //ConfirmationToken ct = confirmationService.generateToken(userService.findUserByEmail(data.getEmail()).getUserId());

        //emailService.sendVerificationEmail(data, "Verify your account", confirmationService.getConfirmLink(ct));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirm")
    public ResponseEntity<User> confirmUser(@RequestParam("token") String confirmationToken) {
        User user = confirmationService.confirmUser(confirmationToken);
        if (user != null) {
            //emailService.sendGreetingEmail(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    /*@PatchMapping("/recover")
    public ResponseEntity changePassword(@RequestBody user newUser, @RequestParam("token") String confirmationToken) throws Exception {
        //User user = userService.updatePassword(newUser, confirmationToken);
        //emailService.sendSuccessfulRecoveryEmail(user);
        return ResponseEntity.ok().build();
    }*/

    /*@GetMapping("/recover")
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
