package htwberlin.webtech.ss24.fitnessplaner.controller;

import java.io.IOException;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            logger.debug("JWT Token found: {}", token);
        } else {
            logger.debug("No JWT Token found in request headers.");
        }

        filterChain.doFilter(request, response);
    }
}