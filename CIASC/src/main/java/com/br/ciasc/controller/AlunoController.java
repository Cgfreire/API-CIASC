package com.br.ciasc.controller;

import com.br.ciasc.models.Aluno;
import com.br.ciasc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/alunos")
    public List<Aluno>lista_alunos(){return alunoRepository.findAll();}

    @PostMapping(value = "/cadastro")
    public Aluno cadastrar(@RequestBody Aluno aluno){return alunoRepository.save(aluno);}

    @PutMapping(value = "/atualiza")
    public Aluno atualiza(@RequestBody Aluno aluno){
        if (aluno.getId()!=null){
            return alunoRepository.save(aluno);
        }
        return null;
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
