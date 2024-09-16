package com.api.api_aluno.Controller;

import java.util.List;


import com.api.api_aluno.domain.dto.ResponseDto;
import com.api.api_aluno.domain.dto.AlunoDto;
import com.api.api_aluno.domain.entity.Aluno;
import com.api.api_aluno.domain.service.AlunoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // http://localhost:8080/aluno/create
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto saveAluno(@Valid @RequestBody Aluno aluno) {
        return alunoService.saveAluno(aluno);
    }

    // http://localhost:8080/aluno/list
    @GetMapping(value = "/list")
    public List<AlunoDto> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    // http://localhost:8080/aluno/{matricula}
    @GetMapping(value = "/{matricula}")
    public AlunoDto getAlunoByMatricula(@Valid @PathVariable Long matricula) {
        return alunoService.getAlunoByMatricula(matricula);
    }

    // http://localhost:8080/aluno/upadate
    @PutMapping(value = "/update/{matricula}")
    public ResponseDto updateAluno(@Valid @RequestBody Aluno aluno) {
        return alunoService.updateAluno(aluno);
    }

    // http://localhost:8080/aluno/{matricula}
    @DeleteMapping(value = "/{matricula}")
    public ResponseDto deleteAluno(@Valid @PathVariable Long matricula) {
        return alunoService.deleteAluno(matricula);
    }


}
