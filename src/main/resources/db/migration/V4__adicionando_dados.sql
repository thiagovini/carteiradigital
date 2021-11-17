INSERT INTO public.usuario
(id, nome, sobrenome, cpf)
VALUES('d9416974-46a5-11ec-81d3-0242ac130003', 'Outro', 'Usuario', '321');

INSERT INTO public.usuario
(id, nome, sobrenome, cpf)
VALUES('d941667c-46a5-11ec-81d3-0242ac130003', 'Thiago', 'Silveira', '123');

INSERT INTO public.carteira
(id, usuario_id, numero_conta, saldo_total)
VALUES('d941667c-46a5-11ec-81d3-0242ac130003', 'd941667c-46a5-11ec-81d3-0242ac130003', '102030', 50);

INSERT INTO public.carteira
(id, usuario_id, numero_conta, saldo_total)
VALUES('d9416a32-46a5-11ec-81d3-0242ac130003', 'd9416974-46a5-11ec-81d3-0242ac130003', '302010', 50);
