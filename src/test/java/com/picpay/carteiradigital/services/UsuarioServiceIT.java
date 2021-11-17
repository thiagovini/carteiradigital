package com.picpay.carteiradigital.services;

import static com.picpay.carteiradigital.entities.EntitiesFactory.persistirUsuario;
import static com.picpay.carteiradigital.entities.EntitiesFactory.umUsuario;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.picpay.carteiradigital.util.ConfigIT;

class UsuarioServiceIT extends ConfigIT {

	@Test
	@DisplayName("Deve retornar um usuário.")
	void deveRetornarUsuario() {

		// given
		var usuario = persistirUsuario(usuarioRepository, umUsuario());

		// when - then
		var usuarioPersistido = usuarioService.buscaUsuarioPeloId(usuario.getId());

		assertThat(usuario.getId()).isEqualTo(usuarioPersistido.getId());
		assertThat(usuario.getNome()).isEqualTo(usuarioPersistido.getNome());
		assertThat(usuario.getSobrenome()).isEqualTo(usuarioPersistido.getSobrenome());
		assertThat(usuario.getCpf()).isEqualTo(usuarioPersistido.getCpf());

	}

	@Nested
	class CaminhoInfeliz {
		
		@Test
		@DisplayName("Não deve retornar usuário.")
		void NaoDeveRetornarUsuario() {

			// given
			var usuario = umUsuario();

			// when - then
			Exception exception = assertThrows(NoSuchElementException.class,
					() -> usuarioService.buscaUsuarioPeloId(usuario.getId()));

			assertThat("No value present").isEqualTo(exception.getMessage());
		}
	}

}
