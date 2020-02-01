package com.giftshop.services;

import com.giftshop.repository.UserDAO;
        import java.util.ArrayList;
        import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

/**.
 * Implementation of Spring class UserDetailsService
 * We will use Email instead of login
 */
@Service
public class CustomAuthService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(final String useremail)
            throws UsernameNotFoundException {
        com.giftshop.models.User userInfo = userDAO.findUserByEmail(useremail);
        List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority a = new SimpleGrantedAuthority(  userInfo.getRole().getRoleName());
            authorities.add(a);
            //creation of Spring User class
        return new User(userInfo.getEmail(), userInfo.getPassword(),  authorities);

    }
}
