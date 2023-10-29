package com.br.ciasc.repositories;

import org.springframework.stereotype.Repository;
import com.br.ciasc.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long>{
    
}
