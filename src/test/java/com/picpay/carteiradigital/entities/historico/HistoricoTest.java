package com.picpay.carteiradigital.entities.historico;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.picpay.carteiradigital.entities.Historico;
import com.picpay.carteiradigital.entities.TipoTransacaoEnum;

class HistoricoTest {

	@Test
	@DisplayName("Deve criar historico quando tipo de transação for TRANSFERENCIA_RECEBIDA_MENSAGERIA.")
	void deveCriarHistoricoQuandoTipoTransacaoForTransferenciaRecebidaMensageria() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var nomeTransferidor = "Nome Transferidor";
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA_MENSAGERIA, null, null, null, nomeTransferidor, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA_MENSAGERIA);
		assertThat(historico.getNomeTransferidor()).isEqualTo(nomeTransferidor);
		assertThat(historico.getValor()).isEqualTo(valor);

	}
	
	@Test
	@DisplayName("Deve criar historico quando tipo de transação for TRANSFERENCIA_RECEBIDA.")
	void deveCriarHistoricoQuandoTipoTransacaoForTransferenciaRecebida() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var nomeTransferidor = "Nome Transferidor";
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA, null, null, null, nomeTransferidor, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA);
		assertThat(historico.getNomeTransferidor()).isEqualTo(nomeTransferidor);
		assertThat(historico.getValor()).isEqualTo(valor);

	}
	
	@Test
	@DisplayName("Deve criar historico quando tipo de transação for PAGAMENTO_CONTA.")
	void deveCriarHistoricoQuandoTipoTransacaoForPagamentoDeConta() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var descricao = "Conta de Luz";
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.PAGAMENTO_CONTA, descricao, null, null, null, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.PAGAMENTO_CONTA);
		assertThat(historico.getDescricao()).isEqualTo(descricao);
		assertThat(historico.getValor()).isEqualTo(valor);

	}
	
	@Test
	@DisplayName("Deve criar historico quando tipo de transação for SAQUE.")
	void deveCriarHistoricoQuandoTipoTransacaoForSaque() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.SAQUE, null, null, null, null, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.SAQUE);
		assertThat(historico.getValor()).isEqualTo(valor);

	}
	
	@Test
	@DisplayName("Deve criar historico quando tipo de transação for DEPOSITO.")
	void deveCriarHistoricoQuandoTipoTransacaoForDeposito() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.DEPOSITO, null, null, null, null, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.DEPOSITO);
		assertThat(historico.getValor()).isEqualTo(valor);

	}

	@Test
	@DisplayName("Deve criar historico quando tipo de transação for TRANSFERENCIA.")
	void deveCriarHistoricoQuandoTipoTransacaoForTransferencia() {

		// given
		var usuarioId = UUID.randomUUID().toString();
		var recebedor = "Nome Recebedor";
		var valor = 100.0;

		// when
		var historico = Historico.criaHistorico(usuarioId, TipoTransacaoEnum.TRANSFERENCIA, null, recebedor, null, null, valor);
		
		// then
		assertThat(historico.getUsuarioId()).isEqualTo(usuarioId);
		assertThat(historico.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.TRANSFERENCIA);
		assertThat(historico.getRecebedor()).isEqualTo(recebedor);
		assertThat(historico.getValor()).isEqualTo(valor);

	}
}
