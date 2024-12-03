
CREATE TABLE membros(
    id int PRIMARY KEY,
    funcao varchar(50),
    lotacao varchar(50),
    setor varchar(50),
    ramal varchar(50),
    FOREIGN KEY (id) REFERENCES usuarios(id)
);

CREATE TABLE patrocinadores(
    id int PRIMARY KEY,
    posicao varchar(20),
    FOREIGN KEY (id) REFERENCES usuarios(id)
);

CREATE TABLE equipes(
                        id SERIAL PRIMARY KEY,
                        patrocinador_id int REFERENCES patrocinadores (id)
);

CREATE TABLE projetos (
    id SERIAL PRIMARY KEY,
    nome varchar(200) NOT NULL,
    unidade varchar(200),
    versao varchar(200),
    objetivo varchar(200),
    data_inicio DATE,
    data_fim DATE,
    prazo DATE,
    tipo varchar(15),
    status varchar(20),
    equipe_id  int REFERENCES equipes (id)
);

CREATE TABLE alinhamentos(

    id SERIAL PRIMARY KEY,
    objetivo_estrategico varchar(150),
    perspectiva varchar(150),
    processo_de_cadeia_de_valor varchar(150),
    principal boolean,
    valor_publico varchar(150),
    resultado_principal varchar(150),
    indicador_estrategico varchar(150),
    projeto_id int REFERENCES projetos(id)
);

CREATE TABLE unidades (
                          id SERIAL PRIMARY KEY,
                          nome varchar(50),
                          direcao varchar(50),
                          setor varchar(50),
                          responsabilidade varchar(50),
                          email varchar(50),
                          nome_servidor varchar(50),
                          ramal varchar(50)
);

CREATE TABLE projeto_unidades (
                                  projeto_id    int REFERENCES projetos (id) ON UPDATE CASCADE ON DELETE CASCADE,
                                  unidade_id int REFERENCES unidades (id) ON UPDATE CASCADE
);

CREATE TABLE equipe_membros(
    equipe_id int REFERENCES equipes (id) ON UPDATE CASCADE ON DELETE CASCADE,
    membro_id int REFERENCES membros (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO public.usuarios (nome,tipo,email,senha) VALUES
                                                           ('Lucas','GERENTE','lucas@mail.com','123'),
                                                           ('Ravel','FUNCIONARIO','ravel@mail.com','123'),
                                                           ('Luis','CLIENTE','Luis@mail.com','123'),
                                                           ('Miguel','ADMIN','MIGUEL@mail.com','123'),
                                                           ('teste','PATROCINADOR','MIGUELu@mail.com','123');
