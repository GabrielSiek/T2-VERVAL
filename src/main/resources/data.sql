-- Inserindo dados na tabela escopo
create table escopo
(
    id                VARCHAR not null,
    produto_final     VARCHAR not null,
    declaracao_escopo VARCHAR not null
);

create table projeto
(
    id        VARCHAR not null,
    tipo      VARCHAR not null,
    status    VARCHAR not null,
    nome      VARCHAR not null,
    escopo_id VARCHAR not null
);


INSERT INTO escopo (id, produto_final, declaracao_escopo) VALUES ('escopo1', 'Produto A', 'Descrição do Escopo A');
INSERT INTO escopo (id, produto_final, declaracao_escopo) VALUES ('escopo2', 'Produto B', 'Descrição do Escopo B');

-- Inserindo dados na tabela projeto

INSERT INTO projeto (id, tipo, status, nome, escopo_id) VALUES ('projeto1', 'TIPO1', 'ATIVO', 'Projeto A', 'escopo1');
INSERT INTO projeto (id, tipo, status, nome, escopo_id) VALUES ('projeto2', 'TIPO2', 'INATIVO', 'Projeto B', 'escopo2');
