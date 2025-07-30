package com.example.demo.security;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Store the authenticated user in session
        HttpSession session = request.getSession();
        Object principal = authentication.getPrincipal(); // this is usually UserDetails
        User user = ((CustomUserDetails) principal).getUser();
        session.setAttribute("user", user);

        // Redirect after login
        response.sendRedirect("/home");
    }
}