create table usuarios(
    id SERIAL primary key,
    nome varchar(100),
    tipo varchar(15),
    email varchar(255) NOT NULL UNIQUE,
    senha varchar(255) NOT NULL
)