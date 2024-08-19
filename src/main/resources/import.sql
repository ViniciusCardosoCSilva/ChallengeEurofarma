INSERT INTO tb_departamento (nome) VALUES ('Marketing');
INSERT INTO tb_departamento (nome) VALUES ('Tecnologia da Informação');
INSERT INTO tb_departamento (nome) VALUES ('Recursos Humanos');
INSERT INTO tb_departamento (nome) VALUES ('Jurídico');

INSERT INTO tb_funcionario(nome, cpf, email, senha, departamento_id) VALUES ('Jorge Juninho Play', '41025896544', 'jorge@gmail.com', '123456',1);
INSERT INTO tb_funcionario(nome, cpf, email, senha, departamento_id) VALUES ('Ana Maria Braga', '1033253265658', 'ana@gmail.com', '123456',2);
INSERT INTO tb_funcionario(nome, cpf, email, senha, departamento_id) VALUES ('Bob Sponja Calça Quadrada Jr', '85874758544', 'bob@gmail.com', '123456',3);

INSERT INTO tb_treinador(nome, email, cpf, senha) VALUES ('Sue Sylvester', 'sue@outlook.com', '74185296322', '123456');

INSERT INTO tb_treinamento(data_criacao, data_ultima_alteracao, treinador_id, corpo_texto, descricao, titulo, status, tipo) VALUES ('2024-08-18', '2024-08-18', 1, 'Corpo de Texto lorem ipsifnvjoe kjso', 'Descrição lorem', 'Titulo Lorem Treinamento', 'PUBLICADO', 'OBRIGATORIO');