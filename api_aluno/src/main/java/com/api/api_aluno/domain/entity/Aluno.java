package com.api.api_aluno.domain.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    Long matricula;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório")
    @Length(message = "Nome com no máximo 50 caracteres", max = 50)
    String nome;

    @Column(name = "idade")
    int idade;

    @Column(name = "email")
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Deve ser um endereço de e-mail bem formado")
    @Length(message = "E-mail com no máximo 50 caracteres", max = 50)
    String email;

}
