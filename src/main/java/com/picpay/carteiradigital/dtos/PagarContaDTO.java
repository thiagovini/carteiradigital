package com.picpay.carteiradigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public final class PagarContaDTO {
	
	private final String usuarioId;
	private final Double valorPagamento;
	private final String descricaoPagamento;

}
