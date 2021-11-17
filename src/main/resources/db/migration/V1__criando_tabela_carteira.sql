CREATE TABLE carteira (
    id                 char(36) NOT NULL,
    usuario_id         char(36) NOT NULL,
    numero_conta        char(255) NOT NULL,
    saldo_total         numeric NOT NULL,
    CONSTRAINT carteira_pkey PRIMARY KEY (id)
);
