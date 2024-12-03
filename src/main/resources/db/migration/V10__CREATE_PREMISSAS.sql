CREATE TABLE PREMISSAS (
    id BIGINT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    acao_para_manutencao VARCHAR(255) NOT NULL,
    projeto_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (projeto_id) REFERENCES PROJETOS(id));

COMMENT ON TABLE PREMISSAS IS 'Tabela de premissas do projeto';
COMMENT ON COLUMN PREMISSAS.id IS 'Identificador da premissa';
COMMENT ON COLUMN PREMISSAS.descricao IS 'Descrição da premissa';
COMMENT ON COLUMN PREMISSAS.acao_para_manutencao IS 'Ação para manutenção da premissa';
COMMENT ON COLUMN PREMISSAS.projeto_id IS 'Identificador do projeto ao qual a premissa pertence';