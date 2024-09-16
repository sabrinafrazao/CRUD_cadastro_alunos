package com.api.api_aluno.domain.exceptions;

import java.time.ZonedDateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationExceptionModel {
    private int status;
    private ZonedDateTime timestamp;
    @SuppressWarnings("rawtypes")
    private List errosList;
}

