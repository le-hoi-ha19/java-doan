package com.example.fashion.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("➡️ ĐÃ VÀO CustomSuccessHandler.onAuthenticationSuccess");

        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(r -> r.getAuthority()).findFirst();

        logger.info("🔍 ROLE hiện tại: {}", roles.orElse("Không có role"));

        if (roles.orElse("").equals("ADMIN")) {
            logger.info("🔀 Điều hướng tới /admin");
            response.sendRedirect("/admin");
        } else if (roles.orElse("").equals("CUSTOMER")) {
            logger.info("🔀 Điều hướng tới /");
            response.sendRedirect("/");
        } else {
            logger.warn("⚠️ ROLE không hợp lệ → Điều hướng tới /error");
            response.sendRedirect("/error");
        }
    }
}
