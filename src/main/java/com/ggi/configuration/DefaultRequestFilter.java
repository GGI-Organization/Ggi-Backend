package com.ggi.configuration;

import com.ggi.service.ClientServiceImpl;
import com.ggi.util.JwtCenter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class DefaultRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ClientServiceImpl userDetailsService;

    @Autowired
    private JwtCenter tokenCenter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token;
        SecurityContext context = SecurityContextHolder.getContext();

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            System.out.println("Token: " + token);
            tokenCenter.setToken(token);
            try {
                username = tokenCenter.getUserName();
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not start with Bearer String.");
        }

        if (username != null && context.getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);
            if (tokenCenter.validate(userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
