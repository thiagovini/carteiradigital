CREATE TABLE historico (
    id                 varchar(36) NOT NULL,
    usuario_id         varchar(36) NOT NULL,
    tipo_transacao     varchar NOT NULL,
    data 			   timestamp not null,
    descricao     	   varchar,
    recebedor     	   varchar,
    nome_transferidor  varchar,
    valor        	   numeric,
    CONSTRAINT historico_pkey PRIMARY KEY (id)
);
