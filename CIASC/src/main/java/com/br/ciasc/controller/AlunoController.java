package com.br.ciasc.controller;

import com.br.ciasc.models.Aluno;
import com.br.ciasc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/alunos")
    public List<Aluno>lista_alunos(){return alunoRepository.findAll();}
    
    @GetMapping(value = "/alunos/{id}")
    public Aluno obterAlunoPorId(@PathVariable("id") Long id) {
    Optional<Aluno> alunoOptional = alunoRepository.findById(id);
    return alunoOptional.get();
    }
    
    @PostMapping(value = "/cadastro")
    public Aluno cadastrar(@RequestBody Aluno aluno){return alunoRepository.save(aluno);}

    @PutMapping(value = "/atualiza/{id}")
    public Aluno atualiza(@PathVariable(value = "id") Long alunoId, @RequestBody Aluno novo_aluno) {
        Aluno alunoExistente = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + alunoId));
        alunoExistente.setNome(novo_aluno.getNome());
        return alunoRepository.save(alunoExistente);
}

    
    @DeleteMapping(value = "/exclui/{id}")
    public String exclui(@PathVariable("id")Long id){
        if(id != null){
            alunoRepository.deleteById(id);
            return "Excluído";
        }
        return null;
    }
}
