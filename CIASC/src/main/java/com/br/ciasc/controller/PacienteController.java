package com.br.ciasc.controller;

import com.br.ciasc.models.Paciente;
import com.br.ciasc.repository.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ciasc")
public class PacienteController{

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = "/pacientes")
    public List<Paciente> lista_pacientes(){
        return pacienteRepository.findAll();
        }


    @PostMapping(value = "/cadastro")
    public Paciente cadastrar(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    
    @PutMapping(value = "/atualiza/{id}")
    public ResponseEntity<Paciente> atualiza(@PathVariable(value = "id") Long pacienteId, @RequestBody Paciente novo_paciente) {
        novo_paciente.setId(novo_paciente.getId());
        novo_paciente.setNome(novo_paciente.getNome());
        final Paciente updatePaciente = pacienteRepository.save(novo_paciente);
        return ResponseEntity.ok(updatePaciente);
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
