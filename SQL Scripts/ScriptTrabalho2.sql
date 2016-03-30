USE TRABALHOSBD2;

CREATE TABLE PAIS (
	nome varchar(50) NOT NULL PRIMARY KEY
);

CREATE TABLE REGIAO (
	nome varchar(50) NOT NULL PRIMARY KEY,
	longitude bigint,
	latitude bigint,
	UNIQUE (latitude,longitude),
	area bigint,
	refpais varchar(50) FOREIGN KEY  REFERENCES PAIS(nome)
);

CREATE TABLE LOCALDEVENDA (
	idnumerico int NOT NULL PRIMARY KEY,
	morada varchar(100),
	refregiao varchar(50) NOT NULL FOREIGN KEY REFERENCES REGIAO(nome),
);

CREATE TABLE PONTODEVENDA (
	idalphanumerico varchar(10) NOT NULL PRIMARY KEY,
	reflocaldevenda int NOT NULL FOREIGN KEY REFERENCES LOCALDEVENDA(idnumerico)
);

CREATE TABLE MAQUINADEVENDA (
	nserie int NOT NULL PRIMARY KEY,
	modelo int,
	refpontodevenda varchar(10) NOT NULL FOREIGN KEY REFERENCES PONTODEVENDA(idalphanumerico)
);

CREATE TABLE FUNCIONARIO (
	idempresa int NOT NULL PRIMARY KEY,
	bi bigint UNIQUE,
	nome varchar(50)
);

CREATE TABLE ASSISTENCIA (
	assistenciatecnica bit NOT NULL,
	recolhamonetaria float NOT NULL,
	data date NOT NULL,
	reffuncionario int NOT NULL FOREIGN KEY REFERENCES FUNCIONARIO(idempresa),
	refmaquinadevenda int NOT NULL FOREIGN KEY REFERENCES MAQUINADEVENDA(nserie),
	PRIMARY KEY (data ,reffuncionario, refmaquinadevenda),
);

CREATE TABLE VIATURA (
	matricula varchar(6) NOT NULL PRIMARY KEY,
);

CREATE TABLE FORNECEDOR (
	nome varchar(50) NOT NULL PRIMARY KEY
);

CREATE TABLE PRODUTO (
	nome varchar(50) NOT NULL PRIMARY KEY,
	imagem image NOT NULL,
	informacao xml NOT NULL,
	reffornecedor varchar(50) NOT NULL FOREIGN KEY REFERENCES FORNECEDOR(nome),
);

CREATE TABLE LOTE (
	validade date NOT NULL,
	precodecompra float NOT NULL,
	PRIMARY KEY (validade, precodecompra),
	quantidade int,
	refproduto varchar(50) NOT NULL FOREIGN KEY REFERENCES PRODUTO(nome)
);

CREATE TABLE STOCKDAVIATURA (
	quantidade int NOT NULL,
	refviatura varchar(6) NOT NULL FOREIGN KEY REFERENCES VIATURA(matricula),
	reflotevalidade date NOT NULL,
	refloteprecodecompra float NOT NULL,
	FOREIGN KEY (reflotevalidade, refloteprecodecompra) REFERENCES LOTE(validade, precodecompra),
	PRIMARY KEY (reflotevalidade, refloteprecodecompra, refviatura),
);


CREATE TABLE QUANTIDADEDEVENDA (
	quantidade int,
	data date,
	reflotevalidade date,
	refloteprecodecompra float,
	refmaquinadevenda int,
	FOREIGN KEY (reflotevalidade, refloteprecodecompra) REFERENCES LOTE(validade, precodecompra),
	FOREIGN KEY (refmaquinadevenda) REFERENCES MAQUINADEVENDA(nserie),
	PRIMARY KEY (data, reflotevalidade, refloteprecodecompra),
);

CREATE TABLE STOCKDAMAQUINADEVENDA (
	quantidade int,
	refmaquinadevenda int FOREIGN KEY REFERENCES MAQUINADEVENDA(nserie),
	reflotevalidade date,
	refloteprecodecompra float,
	FOREIGN KEY (reflotevalidade, refloteprecodecompra) REFERENCES LOTE(validade, precodecompra),
	PRIMARY KEY (reflotevalidade, refloteprecodecompra, refmaquinadevenda),
);

CREATE TABLE ARMAZEM (
	id int NOT NULL PRIMARY KEY,
	localizacao varchar(50),
	refregiao varchar(50) FOREIGN KEY REFERENCES REGIAO(nome) UNIQUE,
);

CREATE TABLE TABELADEPRECOS (
	data date NOT NULL PRIMARY KEY,
	precodevenda float NOT NULL,
	refmaquinadevenda int FOREIGN KEY REFERENCES MAQUINADEVENDA(nserie),
	refproduto varchar(50) FOREIGN KEY REFERENCES PRODUTO(nome)
);

CREATE TABLE FORNECIMENTO (
	data date,
	reffornecedor varchar(50)NOT NULL FOREIGN KEY REFERENCES FORNECEDOR(nome),
	reflotevalidade date NOT NULL,
	refloteprecodecompra float NOT NULL,
	FOREIGN KEY (reflotevalidade,refloteprecodecompra) REFERENCES LOTE(validade,precodecompra),
	PRIMARY KEY (reflotevalidade,refloteprecodecompra),
);

CREATE TABLE STOCKARMAZEM (
	quantidade int,
	refarmazem int FOREIGN KEY REFERENCES ARMAZEM(id),
	reflotevalidade date,
	refloteprecodecompra float,
	FOREIGN KEY (reflotevalidade, refloteprecodecompra) REFERENCES LOTE(validade, precodecompra),
	PRIMARY KEY (reflotevalidade, refloteprecodecompra, refarmazem),
);

CREATE TABLE CONDUTOR (
	data date,
	refviatura varchar(6) FOREIGN KEY REFERENCES VIATURA(matricula) NOT NULL,
	reffuncionario int FOREIGN KEY REFERENCES FUNCIONARIO(idempresa) NOT NULL,
	PRIMARY KEY(data, refviatura, reffuncionario),
);
