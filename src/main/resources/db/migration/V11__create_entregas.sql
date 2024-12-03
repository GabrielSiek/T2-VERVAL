-- src/main/resources/db/migration/V1__Create_Fase_Entrega_MicroEntregas.sql

-- Criação da tabela Fase
CREATE TABLE fases (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    projeto_id int REFERENCES projetos(id)
);

-- Criação da tabela Entrega
CREATE TABLE entregas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    responsavel VARCHAR(255),
    data_inicio DATE,
    data_prevista DATE,
    status VARCHAR(50),
    fase_id INT,
    CONSTRAINT fk_fase
        FOREIGN KEY (fase_id)
        REFERENCES fases(id)
        ON DELETE SET NULL
);

-- Criação da tabela Entrega Requisitos
CREATE TABLE entrega_requisitos (
    entrega_id INT,
    requisito VARCHAR(255),
    CONSTRAINT fk_entrega_requisitos
        FOREIGN KEY (entrega_id)
        REFERENCES entregas(id)
        ON DELETE CASCADE
);

-- Criação da tabela MicroEntregas
CREATE TABLE micro_entregas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    entrega_id INT,
    CONSTRAINT fk_entrega
        FOREIGN KEY (entrega_id)
        REFERENCES entregas(id)
        ON DELETE CASCADE
);

-- Criação da tabela MicroEntregas Itens
CREATE TABLE microentregas_itens (
    microentrega_id INT,
    item VARCHAR(255),
    CONSTRAINT fk_microentregas_itens
        FOREIGN KEY (microentrega_id)
        REFERENCES micro_entregas(id)
        ON DELETE CASCADE
);
