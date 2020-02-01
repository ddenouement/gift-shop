package com.giftshop.config;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**.
 * Invoke tokenProvider
 */
public class JwtTokenFilter extends GenericFilterBean {

    private  TokenProvider jwtTokenProvider;

    public JwtTokenFilter(final  TokenProvider  jwtTokenProvider) {
        this.jwtTokenProvider =  jwtTokenProvider;
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
                         final   FilterChain filterChain)
            throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //attempt to authenticate user by its token
            Authentication auth =
                    jwtTokenProvider.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }
}
