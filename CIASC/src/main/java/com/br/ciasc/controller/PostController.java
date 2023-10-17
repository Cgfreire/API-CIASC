package com.br.ciasc.controller;

import com.br.ciasc.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.ciasc.repository.PostRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/ciasc")
public class PostController{

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = "/pacientes")
    public List<Post> lista_pacientes(){
        return postRepository.findAll();
        }
    
    @GetMapping(value = "/pacientes/{id}")
    public Post obterPacientePorId(@PathVariable("id") Long id) {
    Optional<Post> pacienteOptional = postRepository.findById(id);
    return pacienteOptional.get();
    }
    
    @PostMapping(value = "/cadastro")
    public Post cadastrar(@RequestBody Post paciente){
        return postRepository.save(paciente);
    }
    
    @PutMapping(value = "/atualiza/{id}")
    public Post atualiza(@PathVariable(value = "id") Long pacienteId, @RequestBody Post novo_paciente) {
        Post pacienteExistente = postRepository.findById(pacienteId)
            .orElseThrow(() -> new RuntimeException ("Aluno não encontrado com o ID: "));
    
        pacienteExistente.setNome(novo_paciente.getNome());
        return postRepository.save(pacienteExistente);
    }

    
    @DeleteMapping("/exclui/{id}")
    public String exclui(@PathVariable("id") Long id) {
        if (id != null) {
            postRepository.deleteById(id);
        return "Excluído";
            }
    return null;
    }
}
