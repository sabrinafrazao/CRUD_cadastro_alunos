package com.api.api_aluno.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    Long matricula;
    String nome;;
    int idade;
    String email;
}