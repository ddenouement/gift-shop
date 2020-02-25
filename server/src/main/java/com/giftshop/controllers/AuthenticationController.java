package com.giftshop.controllers;

import com.giftshop.config.TokenProvider;
import com.giftshop.dto.UserLoginRequestDTO;
import com.giftshop.models.User;
import com.giftshop.services.UserService;
import com.giftshop.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for logging in; registration and signing off account
 */
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private IUserService authService;
    /**
     *  Spring class.
     */
    private AuthenticationManager authenticationManager;
    private TokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationController(
                            final UserService authService,
                            final AuthenticationManager authenticationManager,
                            final TokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/user/login")
    public ResponseEntity signIn( @RequestBody UserLoginRequestDTO data, final HttpServletResponse response) {
            return doAuthentication(data.password, data.email, response);
    }

    //TODO приклад як взяти id юзера з запиту
    //тільки залогінений юзер може побачити свій id
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')" )
    @GetMapping(value = "/user/id")
    public int showMyId(@CookieValue("token")   String token ) {
        int my_id =  jwtTokenProvider.getUserId(token);
        return  my_id;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')" )
    @GetMapping(value = "/user/logout")
    public ResponseEntity logout(final HttpServletResponse response) {
        deleteToken(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')" )
    @GetMapping(value = "/user/islogged")
    public int isLogged( ) {
        System.out.println("is logged");
        return  1;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')" )
    @GetMapping(value = "/user/role")
    public ResponseEntity getCurrentUserRole(@CookieValue("token")   String token ) {

            int my_id =  jwtTokenProvider.getUserId(token);
            String role = authService.userRole(my_id);
        Map<Object, Object> model = new HashMap<>();
        model.put("role",  role);
        return ok(model);
    }

    private ResponseEntity doAuthentication( String password, String email, final HttpServletResponse response){
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(email, password));
        User user = authService.findUserByEmail(email);
        String  role = user.getRole().getRoleName();
        List<String> roles = new ArrayList();
        roles.add(role);
        String token = jwtTokenProvider.createToken(email, roles, user.getUserId());
        Map<Object, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("token", token);
        model.put("role",  user.getRole().getRoleName());
        System.out.println("User: " + email + " ; his token: " + token);
        saveToken(response, token);
        return ok(model);
    }


    private void setCookie(final HttpServletResponse response,
                           final String name, final String value, final int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    private void saveToken(final HttpServletResponse response,
                           final String token) {
        setCookie(response, "token", token, 7200); // 2 h
    }

    private void deleteToken(final HttpServletResponse response) {
        setCookie(response, "token", null, 0);

    }

}
