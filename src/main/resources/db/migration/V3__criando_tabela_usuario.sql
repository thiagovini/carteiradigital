CREATE TABLE usuario (
    id          char(36) NOT NULL,
    nome        char(255) NOT NULL,
    sobrenome   char(255) NOT NULL,
    cpf         char(255),
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

