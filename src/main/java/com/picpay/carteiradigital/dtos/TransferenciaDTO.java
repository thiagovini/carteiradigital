package com.picpay.carteiradigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public final class TransferenciaDTO {
	
	private final String transferidorId;
	private final String numeroContaRecebedor;
	private final Double valorTransferido;

}
