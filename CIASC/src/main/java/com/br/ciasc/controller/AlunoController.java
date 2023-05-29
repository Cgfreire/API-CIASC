package com.br.ciasc.controller;

import com.br.ciasc.models.Aluno;
import com.br.ciasc.models.Paciente;
import com.br.ciasc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/alunos")
    public List<Aluno>lista_alunos(){return alunoRepository.findAll();}

    @PostMapping(value = "/cadastro")
    public Aluno cadastrar(@RequestBody Aluno aluno){return alunoRepository.save(aluno);}

    @PutMapping(value = "/atualiza/{id}")
    public ResponseEntity<Aluno> atualiza(@PathVariable(value = "id") Long alunoId, @RequestBody Aluno novo_aluno) {
    Aluno alunoExistente = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + alunoId));
    alunoExistente.setNome(novo_aluno.getNome());   
    final Aluno updateAluno = alunoRepository.save(alunoExistente);
    return ResponseEntity.ok(updateAluno);
    
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
