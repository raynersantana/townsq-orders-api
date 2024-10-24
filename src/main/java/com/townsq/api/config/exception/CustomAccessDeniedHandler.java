package com.townsq.api.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        // Configura o status HTTP para 403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Define o tipo de conteúdo como JSON
        response.setContentType("application/json");

        // Envia uma mensagem personalizada para o erro 403
        response.getWriter().write("{\"message\": \"You don't have access to this resource.\"}");
    }
}