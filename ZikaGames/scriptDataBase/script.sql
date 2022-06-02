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

CREATE TABLE Jogo (
	id						INT 				NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome 					VARCHAR(80) 	NOT NULL,
	classificacaoInd	VARCHAR(10),
	preco					DECIMAL(5,2)	NOT NULL	 
);

CREATE TABLE Aluguel (
	id 					INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT,
	clienteId			INT 			NOT NULL,
	jogoId				INT			NOT NULL,
	dataAluguel			DATE,
	dataDevolucao		DATE,
	valor					DECIMAL(5,2) NOT NULL,
	ativo					BOOLEAN		 DEFAULT 1,
	FOREIGN KEY (clienteId) REFERENCES cliente(id),
	FOREIGN KEY (jogoId) REFERENCES Jogo(id)
);