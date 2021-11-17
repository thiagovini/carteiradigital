package com.picpay.carteiradigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.carteiradigital.dtos.DepositoDTO;
import com.picpay.carteiradigital.dtos.PagarContaDTO;
import com.picpay.carteiradigital.dtos.SaqueDTO;
import com.picpay.carteiradigital.dtos.TransferenciaDTO;
import com.picpay.carteiradigital.entities.Historico;
import com.picpay.carteiradigital.entities.TipoTransacaoEnum;
import com.picpay.carteiradigital.services.CarteiraService;
import com.picpay.carteiradigital.services.HistoricoService;
import com.picpay.carteiradigital.services.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = CarteiraController.PATH)
public class CarteiraController {

	public static final String PATH = "/api/v1/carteira";

	@Autowired
	private CarteiraService carteiraService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HistoricoService historicoService;

	@PostMapping(path = "/transferir")
	public ResponseEntity<Void> transferir(@RequestBody TransferenciaDTO transferenciaDTO) {

		var usuario = usuarioService.buscaUsuarioPeloId(transferenciaDTO.getTransferidorId());

		try {

			carteiraService.transfereValor(transferenciaDTO.getValorTransferido(), usuario.getId());

			carteiraService.recebeValorTransferido(transferenciaDTO.getValorTransferido(),
					transferenciaDTO.getNumeroContaRecebedor());

			var carteiraRecebedor = carteiraService
					.buscaCarteiraPeloNumeroConta(transferenciaDTO.getNumeroContaRecebedor());

			var recebedor = usuarioService.buscaUsuarioPeloId(carteiraRecebedor.get().getUsuarioId());

			historicoService.salvarHistorico(usuario.getId(), TipoTransacaoEnum.TRANSFERENCIA,
					recebedor.getNome() + " " + recebedor.getSobrenome(), null, null, null,
					transferenciaDTO.getValorTransferido());

			historicoService.salvarHistorico(usuario.getId(), TipoTransacaoEnum.TRANSFERENCIA_RECEBIDA,
					recebedor.getNome() + " " + recebedor.getSobrenome(), transferenciaDTO.getNumeroContaRecebedor(),
					null, null, transferenciaDTO.getValorTransferido());

			return ResponseEntity.ok().build();

		} catch (Exception e) {

			return ResponseEntity.badRequest().eTag(e.getMessage()).build();

		}

	}

	@PostMapping(path = "/depositar")
	public ResponseEntity<Void> depositar(@RequestBody DepositoDTO depositoDTO) {

		var usuario = usuarioService.buscaUsuarioPeloId(depositoDTO.getUsuarioId());

		carteiraService.depositar(depositoDTO.getValorDeposito(), usuario.getId());

		historicoService.salvarHistorico(usuario.getId(), TipoTransacaoEnum.DEPOSITO, null, null, null, null,
				depositoDTO.getValorDeposito());

		return ResponseEntity.ok().build();

	}

	@PostMapping(path = "/sacar")
	public ResponseEntity<Void> sacar(@RequestBody SaqueDTO saqueDTO) {

		var usuario = usuarioService.buscaUsuarioPeloId(saqueDTO.getUsuarioId());

		try {

			carteiraService.sacar(saqueDTO.getValorSaque(), usuario.getId());

			historicoService.salvarHistorico(usuario.getId(), TipoTransacaoEnum.SAQUE, null, null, null, null,
					saqueDTO.getValorSaque());

			return ResponseEntity.ok().build();

		} catch (Exception e) {

			return ResponseEntity.badRequest().eTag(e.getMessage()).build();

		}

	}

	@PostMapping(path = "/pagarConta")
	public ResponseEntity<Void> pagarConta(@RequestBody PagarContaDTO pagarContaDTO) {

		var usuario = usuarioService.buscaUsuarioPeloId(pagarContaDTO.getUsuarioId());

		try {

			carteiraService.pagarConta(pagarContaDTO.getValorPagamento(), usuario.getId());

			historicoService.salvarHistorico(usuario.getId(), TipoTransacaoEnum.PAGAMENTO_CONTA, null, null, null,
					pagarContaDTO.getDescricaoPagamento(), pagarContaDTO.getValorPagamento());

			return ResponseEntity.ok().build();

		} catch (Exception e) {

			return ResponseEntity.badRequest().eTag(e.getMessage()).build();

		}

	}

	@GetMapping(path = "/historico/{id}")
	public List<Historico> historico(@PathVariable(value = "id") String id) {

		return historicoService.buscaHistorico(id);

	}
	
	@GetMapping(path = "/saldo/{id}")
	public Double BuscaSaldo(@PathVariable(value = "id") String id) {
		
		return carteiraService.buscaSaldo(id);
	
	}

}
