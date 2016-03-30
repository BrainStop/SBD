USE TRABALHOSBD2;

INSERT INTO PAIS VALUES
('Portugal'),
('Espanha'),
('França');

INSERT INTO REGIAO VALUES 
('Costa Sul', 1000, 1000, 500, 'Portugal'),
('Centro de Espanha', 2094, 3124, 500, 'Espanha'),
('Paris', 1231, 1244, 500, 'França');

INSERT INTO LOCALDEVENDA VALUES 
(1, 'Rua Jerbaisio', 'Costa Sul'),
(2, 'Rua Frederigo Borgues', 'Costa Sul'),
(3, 'Rua Baguete', 'Centro de Espanha');

INSERT INTO PONTODEVENDA VALUES 
('A', 1),
('B', 2),
('C', 3);

INSERT INTO MAQUINADEVENDA VALUES 
(12345, 1, 'A'),
(12346, 2, 'A'),
(12347, 2, 'B');

INSERT INTO FUNCIONARIO VALUES 
(123, 12345678, 'José Almeida'),
(22, 87654321, 'Rui Pendruia'),
(85, 4321678, 'Bernardo Jalimpo');

INSERT INTO ASSISTENCIA VALUES 
(0, 300.20, '2015-05-07', 123, 12345),
(1, 0.0, '2015-04-09', 85, 12346),
(1, 240.30,'2015-10-07', 22, 12347);


INSERT INTO VIATURA VALUES 
('12AA23'),
('BG12NB'),
('69BA25');

INSERT INTO FORNECEDOR VALUES 
('Nestle'),
('BoloseDoces'),
('Bebidas');

INSERT INTO PRODUTO VALUES 
('Kitkat',(SELECT bulkcolumn FROM OPENROWSET( bulk 'D:\LEIM\5º Semestre\SBD\Image\kitkat.png',single_blob) AS BLOB), '<InformacaoNutricional id="Kitkat" peso="45g" valor_energetico="238kcal/1000kJ" carboidratos="28g" proteinas="2,7g" gorduras_totais="13g" fibraAlimentar="0,7g" sodio="36mg" />', 'Nestle'),
('Bollycao',(SELECT bulkcolumn FROM OPENROWSET( bulk 'D:\LEIM\5º Semestre\SBD\Image\bollycao.png',single_blob) AS BLOB) , '<InformacaoNutricional id="Bollycao" peso="57g" valor_energetico="240kcal/1002kJ" carboidratos="32,8g" proteinas="4,6g"	lipidos="9,7g" fibras="1,5g" sodio="150mg" />', 'BoloseDoces'),
('Coca-Cola',(SELECT bulkcolumn FROM OPENROWSET( bulk 'D:\LEIM\5º Semestre\SBD\Image\coca-cola.png',single_blob) AS BLOB) , '<InformacaoNutricional id="Coca-cola" peso="330ml" valor_energetico="42kcal/180kJ" hidratos_carbono="10,6g" />', 'Bebidas');

INSERT INTO LOTE VALUES 
('2017-01-02', 27.32, 42, 'Kitkat'),
('2016-02-05', 12.42, 20, 'Bollycao'),
('2020-08-22', 10.35, 20, 'Coca-Cola');

INSERT INTO TABELADEPRECOS VALUES
('2015-09-01', 1.20, 12345, 'KitKat'),
('2015-09-02', 1.30, 12345, 'Coca-Cola'),
('2015-09-03', 1.80, 12345, 'Bollycao'),
('2015-09-04', 1.20, 12346, 'KitKat'),
('2015-09-05', 1.40, 12346, 'Coca-Cola'),
('2015-09-06', 1.90, 12346, 'Bollycao'),
('2015-09-07', 1.20, 12347, 'KitKat'),
('2015-09-08', 1.15, 12347, 'Coca-Cola'),
('2015-09-09', 1.70, 12347, 'Bollycao');

INSERT INTO FORNECIMENTO VALUES
('2015-10-15', 'Nestle', '2017-01-02', 27.32),
('2015-09-11', 'BoloseDoces', '2016-02-05', 12.42),
('2015-08-13', 'Bebidas', '2020-08-22', 10.35);

INSERT INTO ARMAZEM VALUES 
(1, 'Espanha', 'Costa Sul'),
(2, 'Portugal', 'Centro de Espanha'),
(3, 'Holanda', 'Paris');

INSERT INTO STOCKDAMAQUINADEVENDA VALUES
(30, 12345, '2017-01-02', 27.32),
(30, 12346, '2016-02-05', 12.42),
(30, 12347, '2020-08-22', 10.35);

INSERT INTO STOCKDAVIATURA VALUES
(30, '12AA23', '2017-01-02', 27.32),
(32, 'BG12NB', '2016-02-05', 12.42),
(34, '69BA25', '2020-08-22', 10.35);

INSERT INTO STOCKARMAZEM VALUES
(31, 1, '2017-01-02', 27.32),
(32, 1, '2016-02-05', 12.42),
(33, 3, '2020-08-22', 10.35);

INSERT INTO QUANTIDADEDEVENDA VALUES
(4, '2015-02-02', '2017-01-02', 27.32, 12345),
(5, '2015-02-02', '2016-02-05', 12.42, 12346),
(7, '2015-02-02', '2020-08-22', 10.35, 12347);

INSERT INTO CONDUTOR VALUES
('2015-02-02' ,'12AA23', 123),
('2015-02-04' ,'12AA23', 22),
('2015-08-02' ,'12AA23', 123);
