package com.br.ciasc.controller;

import com.br.ciasc.models.Post;
import com.br.ciasc.models.User;
import com.br.ciasc.repository.PostRepository;
import com.br.ciasc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
        
@CrossOrigin
@RestController
@RequestMapping(value = "/users/{userId}/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Post> listarPosts(@PathVariable("userId") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        return user.getPosts();
    }

    @GetMapping(value = "/{postId}")
    public Post obterPostPorId(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElse(null);
    }

    @PostMapping
    public Post criarPost(@PathVariable("userId") Long userId, @RequestBody Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        post.setUser(user);
        return postRepository.save(post);
    }

    @PutMapping(value = "/{postId}")
    public Post atualizarPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId, @RequestBody Post novoPost) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<Post> postOptional = postRepository.findById(postId);
        Post postExistente = postOptional.orElseThrow(() -> new RuntimeException("Post não encontrado com o ID: " + postId));

        if (postExistente.getUser().getId() == userId) {
            postExistente.setNome(novoPost.getNome());
            return postRepository.save(postExistente);
        } else {
            throw new RuntimeException("Post não pertence ao usuário com o ID: " + userId);
        }
    }

    @DeleteMapping("/{postId}")
    public String excluirPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + userId));
        Optional<Post> postOptional = postRepository.findById(postId);
        Post postExistente = postOptional.orElseThrow(() -> new RuntimeException("Post não encontrado com o ID: " + postId));

        if (postExistente.getUser().getId() == userId) {
            postRepository.delete(postExistente);
            return "Post excluído";
        } else {
            throw new RuntimeException("Post não pertence ao usuário com o ID: " + userId);
        }
    }
}
