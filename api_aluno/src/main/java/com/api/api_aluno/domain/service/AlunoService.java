package com.api.api_aluno.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import com.api.api_aluno.domain.dto.ResponseDto;
import com.api.api_aluno.domain.dto.AlunoDto;
import com.api.api_aluno.domain.entity.Aluno;
import com.api.api_aluno.domain.enumeration.Status;
import com.api.api_aluno.domain.repository.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class   AlunoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDto responseDto;

    AlunoRepository alunoRepository;

    @SuppressWarnings("null")
    public ResponseDto saveAluno(Aluno aluno) {
        responseDto.setMatricula(alunoRepository.save(aluno).getMatricula());
        responseDto.setMenssage("Aluno incluído com sucesso...");
        responseDto.setStatus(Status.SUCCESS.value());
        return responseDto;
    }

    public List<AlunoDto> getAllAlunos() {
        List<AlunoDto> listAllAlunoDto = alunoRepository.findAll().stream()
                .map(Aluno -> modelMapper.map(Aluno, AlunoDto.class))
                .collect(Collectors.toList());
        return listAllAlunoDto;
    }

    public List<AlunoDto> getAllUserOrderByName() {
        List<AlunoDto> listAllAlunoDto = alunoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")).stream()
                .map(Aluno -> modelMapper.map(Aluno, AlunoDto.class))
                .collect(Collectors.toList());
        return listAllAlunoDto;
    }

    @SuppressWarnings("null")
    public AlunoDto getAlunoByMatricula(Long matricula) {
        return modelMapper.map(alunoRepository.findById(matricula).get(), AlunoDto.class);
    }

    @SuppressWarnings("null")
    public ResponseDto updateAluno(Aluno aluno) {
        responseDto.setMatricula(aluno.getMatricula());
        if (alunoRepository.existsById(aluno.getMatricula())) {
            alunoRepository.save(aluno);
            responseDto.setMenssage("Aluno alterado com sucesso...");
            responseDto.setStatus(Status.SUCCESS.value());
        } else {
            responseDto.setMenssage("Matrícula do Aluno inválida...");
            responseDto.setStatus(Status.ERROR.value());
        }
        return responseDto;
    }

    public ResponseDto deleteAluno(Long matricula) {
        responseDto.setMatricula(matricula);
        if (alunoRepository.existsById(matricula)) {
            alunoRepository.deleteById(matricula);
            responseDto.setMenssage("Aluno deletado com sucesso...");
            responseDto.setStatus(Status.SUCCESS.value());
        } else {
            responseDto.setMenssage("Matrícula do Aluno inválida...");
            responseDto.setStatus(Status.ERROR.value());
        }
        return responseDto;
    }

    

}
