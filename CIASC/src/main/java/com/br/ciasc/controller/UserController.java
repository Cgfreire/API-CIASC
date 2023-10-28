package com.br.ciasc.controller;

import com.br.ciasc.models.User;
import com.br.ciasc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<User> listarUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar usuários");
        }
    }

    @GetMapping(value = "/{userId}")
    public User obterUserPorId(@PathVariable("userId") Long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            return userOptional.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter usuário por ID");
        }
    }

    @PostMapping()
    public User cadastrarUser(@RequestBody User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar usuário");
        }
    }

    @PutMapping(value = "/{userId}")
    public User atualizarUser(@PathVariable("userId") Long userId, @RequestBody User novoUser) {
        try {
            User userExistente = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
            userExistente.setNome(novoUser.getNome());
            return userRepository.save(userExistente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar usuário");
        }
    }

    @DeleteMapping(value = "/{userId}")
    public String excluirUser(@PathVariable("userId") Long userId) {
        try {
            if (userId != null) {
                userRepository.deleteById(userId);
                return "Usuário excluído";
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir usuário");
        }
    }
}
