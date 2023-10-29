package com.br.ciasc.controllers;

import com.br.ciasc.models.UserModel;
import com.br.ciasc.repositories.UserRepository;

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
    public List<UserModel> listarUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar usuários");
        }
    }

    @GetMapping(value = "/{userId}")
    public UserModel obterUserPorId(@PathVariable("userId") Long userId) {
        try {
            Optional<UserModel> userOptional = userRepository.findById(userId);
            return userOptional.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter usuário por ID");
        }
    }

    @PostMapping()
    public UserModel cadastrarUser(@RequestBody UserModel user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar usuário");
        }
    }

    @PutMapping(value = "/{userId}")
    public UserModel atualizarUser(@PathVariable("userId") Long userId, @RequestBody UserModel novoUser) {
        try {
            UserModel userExistente = userRepository.findById(userId)
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
