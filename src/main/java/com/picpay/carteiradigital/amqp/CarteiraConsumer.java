package com.picpay.carteiradigital.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.picpay.carteiradigital.configs.AMQPConfig;
import com.picpay.carteiradigital.dtos.TransferenciaOutroBancoDTO;
import com.picpay.carteiradigital.entities.TipoTransacaoEnum;
import com.picpay.carteiradigital.services.CarteiraService;
import com.picpay.carteiradigital.services.HistoricoService;

@Component
public class CarteiraConsumer {

	@Autowired
	private CarteiraService carteiraService;

	@Autowired
	private HistoricoService historicoService;

	@RabbitListener(queues = AMQPConfig.QUEUE)
	public void consumer(TransferenciaOutroBancoDTO transferenciaOutroBancoDTO) throws Exception {

		carteiraService.recebeValorTransferido(transferenciaOutroBancoDTO.getValorTransferido(),
				transferenciaOutroBancoDTO.getNumeroContaRecebedor());

		historicoService.salvarHistorico(null, TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA_MENSAGERIA, null,
				transferenciaOutroBancoDTO.getNumeroContaRecebedor(), transferenciaOutroBancoDTO.getNomeTrasnferidor(),
				null, transferenciaOutroBancoDTO.getValorTransferido());

	}
}