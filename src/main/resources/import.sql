INSERT INTO tb_departamento (nome) VALUES ('Marketing');
INSERT INTO tb_departamento (nome) VALUES ('Tecnologia da Informação');
INSERT INTO tb_departamento (nome) VALUES ('Recursos Humanos');
INSERT INTO tb_departamento (nome) VALUES ('Jurídico');

INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Júlia Martins Oliveira', '41025896544', 'julia@eurofarma.com', '123456', 'FUNCIONARIO',1);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Gustavo Henrique Melo', '43587638724', 'gustavo@eurofarma.com', '123456', 'FUNCIONARIO',1);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Rafael Almeida Santos', '1033253265658', 'rafael@eurofarma.com', '123456', 'FUNCIONARIO',2);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Ana Beatriz Silva', '3456432325678', 'ana@eurofarma.com', '123456', 'FUNCIONARIO',2);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Beatriz Souza Rocha', '85874758544', 'beatriz@eurofarma.com', '123456', 'FUNCIONARIO',3);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Bruno Rodrigues Campos', '12324364575', 'bruno@eurofarma.com', '123456', 'FUNCIONARIO',3)
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('Larissa Campos Cardoso', '77236554205', 'larissa@eurofarma.com', '123456', 'FUNCIONARIO',3)
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('João Pedro Carvalho', '39826418373', 'joao@eurofarma.com', '123456', 'FUNCIONARIO',4);
INSERT INTO tb_funcionario(nome, cpf, email, senha, role, departamento_id) VALUES ('André Luiz Santos', '87756456892', 'andre@eurofarma.com', '123456', 'FUNCIONARIO',4);

--$2a$10$SPr1pPp1fkoSLfEcNKhYSel3ThjPB5LKlPp
INSERT INTO tb_treinador(nome, email, cpf, senha, role) VALUES ('Sue Sylvester', 'sue@outlook.com', '74185296322', '123456', 'TREINADOR');
INSERT INTO tb_treinador(nome, email, cpf, senha, role) VALUES ('Felipe Augusto Martins', 'felipe@eurofarma.com', '98765432100', '123456', 'TREINADOR');
INSERT INTO tb_treinador(nome, email, cpf, senha, role) VALUES ('Miguel Antônio Ferreira', 'miguel@eurofarma.com', '54321098765', '123456', 'TREINADOR');
INSERT INTO tb_treinador(nome, email, cpf, senha, role) VALUES ('Clara Elizabeth Nogueira', 'clara@eurofarma.com', '11223344556', '123456', 'TREINADOR');

INSERT INTO tb_treinamento(data_criacao, data_ultima_alteracao, treinador_id, corpo_texto, descricao, titulo, status, tipo) VALUES ('2024-08-18', '2024-08-18', 1, 'Corpo de Texto treinamento 1 departamento Marketing', 'Descrição do treinamento 1', 'Treinamento de Marketing', 'PUBLICADO', 'OBRIGATORIO');
INSERT INTO tb_treinamento(data_criacao, data_ultima_alteracao, treinador_id, corpo_texto, descricao, titulo, status, tipo) VALUES ('2024-09-27', '2024-09-27', 2, 'Corpo de Texto treinamento 2 departamento Tecnologia da Informação', 'Descrição  do treinamento 2', 'Treinamento de T.I.', 'PUBLICADO', 'OBRIGATORIO');
INSERT INTO tb_treinamento(data_criacao, data_ultima_alteracao, treinador_id, corpo_texto, descricao, titulo, status, tipo) VALUES ('2024-08-24', '2024-08-24', 3, 'Corpo de Texto treinamento 3 departamento Recurosos Humanos', 'Descrição  do treinamento 3 ', 'Treinamento de RH', 'PUBLICADO', 'OBRIGATORIO');
INSERT INTO tb_treinamento(data_criacao, data_ultima_alteracao, treinador_id, corpo_texto, descricao, titulo, status, tipo) VALUES ('2024-09-10', '2024-09-10', 4, 'Corpo de Texto treinamento 4 departamento Jurídico', 'Descrição  do treinamento 4', 'Treinamento de Jurídico', 'PUBLICADO', 'OBRIGATORIO');

INSERT INTO tb_treinamento_departamento(departamento_id, treinamento_id) VALUES (1,1);
INSERT INTO tb_treinamento_departamento(departamento_id, treinamento_id) VALUES (2,2);
INSERT INTO tb_treinamento_departamento(departamento_id, treinamento_id) VALUES (3,3);
INSERT INTO tb_treinamento_departamento(departamento_id, treinamento_id) VALUES (4,4);

INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (1,1);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (2,1);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (3,2);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (4,2);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (5,3);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (6,3);
INSERT INTO tb_treinamento_funcionario(funcionario_id, treinamento_id) VALUES (7,4);
