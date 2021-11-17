package com.picpay.carteiradigital.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.picpay.carteiradigital.entities.EntitiesFactory;
import com.picpay.carteiradigital.util.ApplicationConfigIT;

class HistoricoServiceIT extends ApplicationConfigIT {

	@Test
	@DisplayName("Deve retornar historico pelo id do usuário.")
	void deveRetornarHistoricoPeloIdDoUsuario() {

		// given
		var historico = EntitiesFactory.persistirHistorico(historicoRepository, EntitiesFactory.umHistorico());

		// when - then
		var historicoPersistido = historicoService.buscaHistorico(historico.getUsuarioId());

		assertThat(historicoPersistido.size()).isEqualTo(1);

	}

	@Test
	@DisplayName("Deve salvar historico.")
	void deveSalvaHistorico() {
		
		// given
		var historico = EntitiesFactory.umHistorico();

		// when
		historicoService.salvarHistorico(historico.getUsuarioId(), historico.getTipoTransacao(), null, null, null, null,
				historico.getValor());
		
		// then
		var historicoPersistido = historicoRepository.findById(historico.getId());
		
		assertThat(historicoPersistido.isPresent());
	}
	
	@Test
	@DisplayName("Deve salvar historico vindo da mensageria.")
	void deveSalvaHistoricoMensageria() {
		
		// given
		var historico = EntitiesFactory.umHistoricoMensageria();
		
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());

		// when
		historicoService.salvarHistorico(historico.getUsuarioId(), historico.getTipoTransacao(), null, carteira.getNumeroConta(), historico.getNomeTransferidor(), null,
				historico.getValor());
		
		// then
		var historicoPersistido = historicoRepository.findById(historico.getId());
		
		assertThat(historicoPersistido.isPresent());
	}
}
