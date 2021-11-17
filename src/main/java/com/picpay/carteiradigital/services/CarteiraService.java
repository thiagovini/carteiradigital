package com.picpay.carteiradigital.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.carteiradigital.entities.Carteira;
import com.picpay.carteiradigital.repositories.CarteiraRepository;

@Service
public class CarteiraService {

	@Autowired
	CarteiraRepository carteiraRepository;

	public void transfereValor(Double valorTransferido, String usuarioId) throws Exception {

		var carteira = carteiraRepository.findByUsuarioId(usuarioId);

		if (carteira.isEmpty())
			throw new Exception("Não existe uma carteira vinculada para o usuário");

		carteira.get().transfereValor(valorTransferido);

		carteiraRepository.save(carteira.get());

	}

	public void recebeValorTransferido(Double valorTransferido, String numeroContaRecebedor) throws Exception {

		var carteira = carteiraRepository.findByNumeroConta(numeroContaRecebedor);

		if (carteira.isEmpty())
			throw new Exception("Essa conta não existe");

		carteira.get().recebeValorTransferido(valorTransferido);

		carteiraRepository.save(carteira.get());

	}

	public void depositar(Double valorDeposito, String usuarioId) {

		var carteira = carteiraRepository.findByUsuarioId(usuarioId);

		carteira.get().depositar(valorDeposito);

		carteiraRepository.save(carteira.get());

	}

	public void sacar(Double valorSaque, String usuarioId) throws Exception {

		var carteira = carteiraRepository.findByUsuarioId(usuarioId);

		carteira.get().sacar(valorSaque);

		carteiraRepository.save(carteira.get());

	}

	public void pagarConta(Double valorPagamento, String usuarioId) throws Exception {

		var carteira = carteiraRepository.findByUsuarioId(usuarioId);

		carteira.get().pagarConta(valorPagamento);

		carteiraRepository.save(carteira.get());

	}
	
	public Optional<Carteira> buscaCarteiraPeloNumeroConta(String numeroConta) {
		
		return carteiraRepository.findByNumeroConta(numeroConta);
		
	}

	public Double buscaSaldo(String id) {
		
		return carteiraRepository.findById(id).orElseThrow().getSaldoTotal();
		
	}

}
