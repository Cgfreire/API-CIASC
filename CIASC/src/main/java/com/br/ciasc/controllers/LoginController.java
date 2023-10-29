package com.br.ciasc.controllers;

import com.br.ciasc.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("auth/login")
    @Operation(parameters = {
            @Parameter(name = "email", schema = @Schema(type = "string")),
            @Parameter(name = "senha", schema = @Schema(type = "string", format = "password"))
    })
    public String login(String email, String senha) {
        try {
            String token = authService.autenticarUsuario(email, senha);
            return token;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }
    }
}
