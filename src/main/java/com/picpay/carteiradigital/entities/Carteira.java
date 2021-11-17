package com.picpay.carteiradigital.entities;

import javax.persistence.Entity;
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
public class Carteira {

	@Id
	private String id;
	private String usuarioId;
	private String numeroConta;
	private Double saldoTotal;

	public void transfereValor(Double valorTransferido) throws Exception {

		validaSaldo(valorTransferido);

		this.saldoTotal -= valorTransferido;

	}

	public void recebeValorTransferido(Double valorTransferido) {

		this.saldoTotal += valorTransferido;

	}

	public void depositar(Double valorDeposito) {

		this.saldoTotal += valorDeposito;

	}

	public void sacar(Double valorSaque) throws Exception {
		
		validaSaldo(valorSaque);
		
		this.saldoTotal -= valorSaque;

	}
	
	public void pagarConta(Double valorPagamento) throws Exception {

		validaSaldo(valorPagamento);
		
		this.saldoTotal -= valorPagamento;
		
	}

	private void validaSaldo(Double valor) throws Exception {

		if (this.getSaldoTotal() < valor)
			throw new Exception("Saldo Insuficiente");

	}

}
