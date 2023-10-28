package com.br.ciasc.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ciasc.models.User;
import com.br.ciasc.repository.UserRepository;
import com.br.ciasc.util.JWTUtil;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public String autenticarUsuario(String email, String senha) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getSenha().equals(senha)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            String token = jwtUtil.geraTokenUsuario(user.getEmail(), claims);
            return token;
        }
        throw new RuntimeException("Credenciais inválidas");
    }
}
