CREATE DATABASE zikagames;

USE zikagames;

CREATE TABLE Cliente (
	id 					INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome 					VARCHAR(80) NOT NULL,
	cpf 					VARCHAR(11) NOT NULL,
	dataNascimento 	DATE,
	telefone				VARCHAR(11),
	email					VARCHAR(80)
);