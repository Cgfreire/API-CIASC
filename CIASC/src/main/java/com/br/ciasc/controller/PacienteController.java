package com.br.ciasc.controller;

import com.br.ciasc.models.Paciente;
import com.br.ciasc.repository.PacienteRepository;
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

@CrossOrigin
@RestController
@RequestMapping(value = "/ciasc")
public class PacienteController{

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = "/pacientes")
    public List<Paciente> lista_pacientes(){
        return pacienteRepository.findAll();
        }
    
    @GetMapping(value = "/pacientes/{id}")
    public Paciente obterPacientePorId(@PathVariable("id") Long id) {
    Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
    return pacienteOptional.get();
    }
    
    @PostMapping(value = "/cadastro")
    public Paciente cadastrar(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    
    @PutMapping(value = "/atualiza/{id}")
    public Paciente atualiza(@PathVariable(value = "id") Long pacienteId, @RequestBody Paciente novo_paciente) {
        Paciente pacienteExistente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new RuntimeException ("Aluno não encontrado com o ID: " + pacienteId));
    
        pacienteExistente.setNome(novo_paciente.getNome());
        return pacienteRepository.save(pacienteExistente);
    }

    
    @DeleteMapping("/exclui/{id}")
    public String exclui(@PathVariable("id") Long id) {
        if (id != null) {
            pacienteRepository.deleteById(id);
        return "Excluído";
            }
    return null;
    }
}
