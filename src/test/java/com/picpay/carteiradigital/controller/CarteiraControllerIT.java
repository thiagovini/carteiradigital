package com.picpay.carteiradigital.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.picpay.carteiradigital.dtos.DepositoDTO;
import com.picpay.carteiradigital.dtos.PagarContaDTO;
import com.picpay.carteiradigital.dtos.SaqueDTO;
import com.picpay.carteiradigital.dtos.TransferenciaDTO;
import com.picpay.carteiradigital.entities.EntitiesFactory;
import com.picpay.carteiradigital.util.ConfigIT;
import com.picpay.carteiradigital.util.TestUtils;

class CarteiraControllerIT extends ConfigIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveTransferirValor() throws Exception {

		var usuario = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umUsuario());

		var usuarioTransferidor = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umOutroUsuario());

		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository,
				EntitiesFactory.umaCarteira(usuario.getId()));

		EntitiesFactory.persistirCarteira(carteiraRepository,
				EntitiesFactory.umaOutraCarteiraComUsuario(usuarioTransferidor.getId()));

		var transferenciaDTO = TransferenciaDTO.builder().transferidorId(usuarioTransferidor.getId())
				.numeroContaRecebedor(carteira.getNumeroConta()).valorTransferido(20.0).build();

		var url = CarteiraController.PATH + "/transferir";

		mockMvc.perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(transferenciaDTO)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void deveDepositarValor() throws Exception {

		var usuario = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umUsuario());

		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository,
				EntitiesFactory.umaCarteira(usuario.getId()));

		var depositoDTO = DepositoDTO.builder().usuarioId(carteira.getUsuarioId()).valorDeposito(1000.0).build();

		var url = CarteiraController.PATH + "/depositar";

		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(depositoDTO)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void deveSacarValor() throws Exception {

		var usuario = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umUsuario());

		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository,
				EntitiesFactory.umaCarteira(usuario.getId()));

		var daqueDTO = SaqueDTO.builder().usuarioId(carteira.getUsuarioId()).valorSaque(15.0).build();

		var url = CarteiraController.PATH + "/sacar";

		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(daqueDTO)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void devePagarConta() throws Exception {

		var usuario = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umUsuario());

		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository,
				EntitiesFactory.umaCarteira(usuario.getId()));

		var pagarContaDTO = PagarContaDTO.builder().usuarioId(carteira.getUsuarioId())
				.descricaoPagamento("Conta de Luz").valorPagamento(50.0).build();

		var url = CarteiraController.PATH + "/pagarConta";

		mockMvc.perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(pagarContaDTO)))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void deveBuscarHistoricoPeloId() throws Exception {

		var usuario = EntitiesFactory.persistirUsuario(usuarioRepository, EntitiesFactory.umUsuario());

		EntitiesFactory.persistirHistorico(historicoRepository, EntitiesFactory.umHistorico(usuario.getId()));

		EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira(usuario.getId()));

		var url = CarteiraController.PATH + "/historico/" + usuario.getId();

		mockMvc.perform(get(url)).andExpect(status().is2xxSuccessful());

	}
	
	@Test
	void deveBuscarSaldoPeloId() throws Exception {

		var carteira = EntitiesFactory.persistirCarteira(carteiraRepository, EntitiesFactory.umaCarteira());

		var url = CarteiraController.PATH + "/saldo/" + carteira.getId();

		mockMvc.perform(get(url)).andExpect(status().is2xxSuccessful());

	}

}
