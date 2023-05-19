package com.br.ciasc.controller;

import com.br.ciasc.models.Paciente;
import com.br.ciasc.repository.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

public class PacienteController {
@RestController
@RequestMapping(value = "/ciasc")
public class GameController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping(value = "/pacientes")
    public List<Paciente> lista_pacientes(){
        return pacienteRepository.findAll();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente cadastrar(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    
    @PutMapping(value = "/altera_paciente/{id}")
    
    
    @DeleteMapping(value = "/apaga_paciente/ {id}")
}
