create database banco;

GRANT ALL PRIVILEGES ON banco. *
TO 'root'@'localhost';

USE banco;

CREATE TABLE aluno (
    matricula BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT,
    email VARCHAR(50) NOT NULL
);
