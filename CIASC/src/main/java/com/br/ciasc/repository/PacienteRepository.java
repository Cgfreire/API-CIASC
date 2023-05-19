package com.br.ciasc.repository;

import org.springframework.stereotype.Repository;
import com.br.ciasc.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
