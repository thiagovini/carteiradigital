package com.picpay.carteiradigital.entities;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.picpay.carteiradigital.repositories.CarteiraRepository;
import com.picpay.carteiradigital.repositories.HistoricoRepository;
import com.picpay.carteiradigital.repositories.UsuarioRepository;

public class EntitiesFactory {

	public static Usuario persistirUsuario(UsuarioRepository repository, Usuario usuario) {
		return repository.save(usuario);
	}

	public static Historico persistirHistorico(HistoricoRepository repository, Historico historico) {
		return repository.save(historico);
	}

	public static Carteira persistirCarteira(CarteiraRepository repository, Carteira carteira) {
		return repository.save(carteira);
	}

	public static Historico.HistoricoBuilder umHistoricoBuilder() {
		return Historico.builder().id(UUID.randomUUID().toString()).usuarioId(UUID.randomUUID().toString())
				.tipoTransacao(TipoTransacaoEnum.SAQUE).data(ZonedDateTime.now()).valor(100.0);
	}
	
	public static Historico umHistorico() {
		return umHistoricoBuilder().build();
	}
	
	public static Historico umHistorico(String usuarioId) {
		return umHistoricoBuilder().usuarioId(usuarioId).build();
	}

	public static Historico umHistoricoMensageria() {
		return Historico.builder().id(UUID.randomUUID().toString()).usuarioId(UUID.randomUUID().toString())
				.tipoTransacao(TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA_MENSAGERIA).data(ZonedDateTime.now())
				.nomeTransferidor("Outro Usuario").valor(100.0).build();
	}

	public static Carteira.CarteiraBuilder umaCarteiraBuilder() {

		return Carteira.builder().id(UUID.randomUUID().toString()).usuarioId(UUID.randomUUID().toString())
				.numeroConta("12345").saldoTotal(100.0);

	}

	public static Carteira umaCarteira() {

		return umaCarteiraBuilder().build();

	}

	public static Carteira umaOutraCarteiraComUsuario(String usuarioId) {

		return umaCarteiraBuilder().usuarioId(usuarioId).numeroConta("54321").build();

	}

	public static Carteira umaCarteira(String usuarioId) {

		return umaCarteiraBuilder().usuarioId(usuarioId).build();

	}

	public static Usuario umUsuario() {
		return Usuario.builder().id(UUID.randomUUID().toString()).nome("Nome").sobrenome("Sobrenome").cpf("123")
				.build();
	}

	public static Usuario umOutroUsuario() {
		return Usuario.builder().id(UUID.randomUUID().toString()).nome("Outro").sobrenome("Usuario").cpf("321").build();
	}

}
