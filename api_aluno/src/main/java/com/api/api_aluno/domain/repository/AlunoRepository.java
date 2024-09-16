package com.api.api_aluno.domain.repository;



import com.api.api_aluno.domain.entity.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,Long>  {
  

}
