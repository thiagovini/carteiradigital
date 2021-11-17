package com.picpay.carteiradigital.services;

import static com.picpay.carteiradigital.entities.TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA;
import static com.picpay.carteiradigital.entities.TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA_MENSAGERIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.carteiradigital.entities.Historico;
import com.picpay.carteiradigital.entities.TipoTransacaoEnum;
import com.picpay.carteiradigital.repositories.HistoricoRepository;


@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Autowired
	CarteiraService carteiraService;
	
	public void salvarHistorico(String usuarioId, TipoTransacaoEnum tipoTransacao, String recebedor, String numeroConta, String nomeTransferidor, String descricao, Double valor) {
		
		String recebedorId = null;
		
		if (TRANSFERENCIA_RECEBIDA == tipoTransacao || TRANSFERENCIA_RECEBIDA_MENSAGERIA == tipoTransacao) {
			recebedorId = carteiraService.buscaCarteiraPeloNumeroConta(numeroConta).get().getUsuarioId();
		}
		
		var historico = Historico.criaHistorico(usuarioId, tipoTransacao, descricao, recebedor, recebedorId, nomeTransferidor, valor);
		
		historicoRepository.save(historico);
		
	}

	public List<Historico> buscaHistorico(String usuarioId) {
		
		return historicoRepository.findByUsuarioId(usuarioId);
		
	}

}
