package com.br.ciasc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ciasc.models.PostModel;
import com.br.ciasc.repositories.PostRepository;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class IndexPostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/all")
    public List<PostModel> listarTodosOsPosts() {
        return postRepository.findAll();
    }
}