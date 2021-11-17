package com.picpay.carteiradigital.entities.carteira;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.picpay.carteiradigital.entities.EntitiesFactory;

class CarteiraTest {

	@Test
	@DisplayName("Deve transferir valor.")
	void deveTransferirValor() throws Exception {

		// given
		var carteira = EntitiesFactory.umaCarteira();

		// when
		carteira.transfereValor(50.0);

		// then
		assertThat(carteira.getSaldoTotal()).isEqualByComparingTo(50.0);

	}
	
	@Test
	@DisplayName("Deve receber valor.")
	void deveReceberValor() {

		// given
		var carteira = EntitiesFactory.umaCarteira();

		// when
		carteira.recebeValorTransferido(50.0);

		// then
		assertThat(carteira.getSaldoTotal()).isEqualByComparingTo(150.0);

	}
	
	@Test
	@DisplayName("Deve depositar valor.")
	void deveDepositarValor() {

		// given
		var carteira = EntitiesFactory.umaCarteira();

		// when
		carteira.depositar(50.0);

		// then
		assertThat(carteira.getSaldoTotal()).isEqualByComparingTo(150.0);

	}
	
	@Test
	@DisplayName("Deve sacar valor.")
	void deveSacarValor() throws Exception {

		// given
		var carteira = EntitiesFactory.umaCarteira();

		// when
		carteira.sacar(100.0);

		// then
		assertThat(carteira.getSaldoTotal()).isEqualByComparingTo(0.0);

	}

	@Test
	@DisplayName("Deve pagar conta.")
	void devePagarConta() throws Exception {

		// given
		var carteira = EntitiesFactory.umaCarteira();

		// when
		carteira.pagarConta(100.0);

		// then
		assertThat(carteira.getSaldoTotal()).isEqualByComparingTo(0.0);

	}

	@Nested
	class CaminhoInfeliz {

		@Test
		@DisplayName("Não deve transferer valor quando saldo for insuficiente.")
		void naoDeveTransferirValorQuandoSaldoForInsuficiente() {

			// given
			var carteira = EntitiesFactory.umaCarteira();

			// when
			Exception exception = assertThrows(Exception.class, () -> carteira.transfereValor(200.0));
			
			// then
			assertThat("Saldo Insuficiente").isEqualTo(exception.getMessage());
		}

	}

}
