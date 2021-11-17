package com.picpay.carteiradigital.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.picpay.carteiradigital.entities.EntitiesFactory;
import com.picpay.carteiradigital.util.ApplicationConfigIT;

class CarteiraServiceIT extends ApplicationConfigIT {
	
	@Test
	void deveTransferirValor() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when
		carteiraService.transfereValor(50.0, carteira.getUsuarioId());
		
		// then
		var carteiraPersistida = carteiraRepository.findById(carteira.getId()).get();
		
		assertThat(carteiraPersistida.getSaldoTotal()).isEqualByComparingTo(50.0);
		
	}
	
	@Test
	void deveReceberValor() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when
		carteiraService.recebeValorTransferido(100.0, carteira.getNumeroConta());
		
		// then
		var carteiraPersistida = carteiraRepository.findById(carteira.getId()).get();
		
		assertThat(carteiraPersistida.getSaldoTotal()).isEqualByComparingTo(200.0);
		
	}
	
	@Test
	void deveDepositarValor() {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when
		carteiraService.depositar(100.0, carteira.getUsuarioId());
		
		// then
		var carteiraPersistida = carteiraRepository.findById(carteira.getId()).get();
		
		assertThat(carteiraPersistida.getSaldoTotal()).isEqualByComparingTo(200.0);
		
	}
	
	@Test
	void deveSacarValor() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when
		carteiraService.sacar(100.0, carteira.getUsuarioId());
		
		// then
		var carteiraPersistida = carteiraRepository.findById(carteira.getId()).get();
		
		assertThat(carteiraPersistida.getSaldoTotal()).isEqualByComparingTo(0.0);
		
	}
	
	@Test
	void devePagarConta() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when
		carteiraService.pagarConta(100.0, carteira.getUsuarioId());
		
		// then
		var carteiraPersistida = carteiraRepository.findById(carteira.getId()).get();
		
		assertThat(carteiraPersistida.getSaldoTotal()).isEqualByComparingTo(0.0);
		
	}
	
	@Test
	void deveBuscarCarteiraPeloNumeroDaConta() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when - then
		var carteiraPersistida = carteiraService.buscaCarteiraPeloNumeroConta(carteira.getNumeroConta());
		
		assertThat(carteiraPersistida.isPresent());
		
	}
	
	@Test
	void deveBuscarSaldoPeloIdDaCateira() throws Exception {
		
		// given
		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());
		
		// when - then
		var saldo = carteiraService.buscaSaldo(carteira.getId());
		
		assertThat(saldo).isEqualByComparingTo(100.0);
		
	}
	
	@Nested
	class CaminhoInfeliz {
		
		@Test
		@DisplayName("Não deve retornar usuário.")
		void NaoDeveTransferirValorQuandoCarteiraNaoExiste() {

			// given
			var carteira = EntitiesFactory.umaCarteira();

			// when - then
			Exception exception = assertThrows(Exception.class,
					() -> carteiraService.transfereValor(100.0, carteira.getUsuarioId()));

			assertThat("Não existe uma carteira vinculada para o usuário").isEqualTo(exception.getMessage());
		}
		
		@Test
		@DisplayName("Não deve retornar usuário.")
		void NaoDeveReceberValorTransferidoQuandoCarteiraNaoExiste() {

			// given
			var carteira = EntitiesFactory.umaCarteira();

			// when - then
			Exception exception = assertThrows(Exception.class,
					() -> carteiraService.recebeValorTransferido(100.0, carteira.getUsuarioId()));

			assertThat("Essa conta não existe").isEqualTo(exception.getMessage());
		}
	}
}
