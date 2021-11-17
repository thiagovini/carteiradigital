package com.picpay.carteiradigital.services.command;

import com.picpay.carteiradigital.entities.TipoTransacaoEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriaHistoricoCommand {
	
	private String id;
	private String usuarioId;
	private TipoTransacaoEnum tipoTransacao;
	private String descricao;
	private String recebedor;

}
