package com.br.ciasc.controllers;

import com.br.ciasc.models.PostModel;
import com.br.ciasc.models.UserModel;
import com.br.ciasc.repositories.PostRepository;
import com.br.ciasc.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/users/{userId}/posts")
public class AdminPostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<PostModel> listarPosts(@PathVariable("userId") Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        return user.getPosts();
    }

    @GetMapping(value = "/{postId}")
    public PostModel obterPostPorId(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<PostModel> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
    }

    @PostMapping
    public PostModel criarPost(@PathVariable("userId") Long userId, @RequestBody PostModel post) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        post.setUser(user);
        return postRepository.save(post);
    }

    @PutMapping(value = "/{postId}")
    public PostModel atualizarPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId,
            @RequestBody PostModel novoPost) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<PostModel> postOptional = postRepository.findById(postId);
        PostModel postExistente = postOptional
                .orElseThrow(() -> new RuntimeException("Post não encontrado com o ID: " + postId));

        if (postExistente.getUser().getId() == userId) {
            postExistente.setAutor(novoPost.getAutor());
            return postRepository.save(postExistente);
        } else {
            throw new RuntimeException("Post não pertence ao usuário com o ID: " + userId);
        }
    }

    @DeleteMapping("/{postId}")
    public String excluirPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<PostModel> postOptional = postRepository.findById(postId);
        PostModel postExistente = postOptional
                .orElseThrow(() -> new RuntimeException("Post não encontrado com o ID: " + postId));

        if (postExistente.getUser().getId() == userId) {
            postRepository.delete(postExistente);
            return "Post excluído";
        } else {
            throw new RuntimeException("Post não pertence ao usuário com o ID: " + userId);
        }
    }
}
