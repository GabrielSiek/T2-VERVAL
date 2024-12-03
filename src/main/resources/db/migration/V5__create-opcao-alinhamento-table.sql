INSERT INTO public.equipe_membros (equipe_id,membro_id) VALUES
                                                            (1,1),
                                                            (1,2),
                                                            (2,3),
                                                            (2,1);
INSERT INTO public.unidades (nome,direcao,setor,responsabilidade,email,nome_servidor,ramal) VALUES
                                                                                                   ('unidade 1','teste','teste','teste','teste','teste','teste'),
                                                                                                   ('unidade 2','teste','teste','teste','teste','teste','teste');

create table opcoes_alinhamento_estrategico (
    id SERIAL PRIMARY KEY,
    texto varchar(250),
    tipo varchar(50)
);

create table objetivos_estrategicos (
    id SERIAL PRIMARY KEY,
    objetivo varchar(200),
    perspectiva varchar(200)
);

create table indicadores_estrategicos (
    id SERIAL PRIMARY KEY,
    texto varchar(300),
    objetivos_estrategicos_id int REFERENCES objetivos_estrategicos (id)
);
