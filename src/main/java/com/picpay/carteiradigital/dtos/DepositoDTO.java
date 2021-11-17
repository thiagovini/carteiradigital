package com.picpay.carteiradigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public final class DepositoDTO {
	
	private final String usuarioId;
	private final Double valorDeposito; 

}
