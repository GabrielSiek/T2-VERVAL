-- Criação da tabela 'restricoes' com campo 'tipo' como VARCHAR(20)
CREATE TABLE restricoes (
                            id SERIAL PRIMARY KEY,
                            tipo VARCHAR(20) NOT NULL,
                            descricao VARCHAR(255) NOT NULL,
                            dificuldade_gerada VARCHAR(100),
                            projeto_id INT REFERENCES projetos(id) ON DELETE SET NULL
);

-- Inserção das três restrições iniciais associadas aos projetos com ID 1 e 2
INSERT INTO restricoes (tipo, descricao, dificuldade_gerada, projeto_id)
VALUES
    ('TEMPO_CRONOGRAMA', 'Restrição de tempo no cronograma do projeto', 'Alta', 1),
    ('QUALIDADE', 'Padrões de qualidade exigidos para a execução do projeto', 'Média', 1),
    ('ESCOPO', 'Limitações no escopo e nas funcionalidades do projeto', 'Baixa', 2);
