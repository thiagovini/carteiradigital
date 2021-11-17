package com.picpay.carteiradigital.dtos;

import lombok.Data;

@Data
public final class PagarContaDTO {
	
	private final String usuarioId;
	private final Double valorPagamento;
	private final String descricaoPagamento;

}
