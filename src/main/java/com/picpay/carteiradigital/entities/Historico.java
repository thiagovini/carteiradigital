package com.picpay.carteiradigital.entities;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Historico {

	@Id
	private String id;
	private String usuarioId;
	@Enumerated(EnumType.STRING)
	private TipoTransacaoEnum tipoTransacao;
	private ZonedDateTime data;
	private String descricao;
	private String recebedor;
	private String nomeTransferidor;
	private Double valor;

	public static Historico criaHistorico(String usuarioId, TipoTransacaoEnum tipoTransacao, String descricao,
			String recebedor, String recebedorId, String nomeTransferidor, Double valor) {

		Historico historico;

		switch (tipoTransacao) {

		case TRANSFERENCIA:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).valor(valor).recebedor(recebedor).build();

			return historico;

		case DEPOSITO:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).valor(valor).build();

			return historico;

		case SAQUE:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).valor(valor).build();

			return historico;

		case PAGAMENTO_CONTA:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).descricao(descricao).valor(valor).build();

			return historico;

		case TRANSFERENCIA_RECEBIDA:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).nomeTransferidor(nomeTransferidor).valor(valor).build();

			return historico;
			
		case TRANSFERENCIA_RECEBIDA_MENSAGERIA:

			historico = Historico.builder().id(UUID.randomUUID().toString()).usuarioId(usuarioId)
					.tipoTransacao(tipoTransacao).data(ZonedDateTime.now()).descricao(descricao).valor(valor)
					.nomeTransferidor(nomeTransferidor).build();

			return historico;
			
		default:
			return null;
		}

	}

}
