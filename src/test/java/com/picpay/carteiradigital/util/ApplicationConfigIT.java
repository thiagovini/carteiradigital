package com.picpay.carteiradigital.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import com.picpay.carteiradigital.repositories.CarteiraRepository;
import com.picpay.carteiradigital.repositories.HistoricoRepository;
import com.picpay.carteiradigital.repositories.UsuarioRepository;
import com.picpay.carteiradigital.services.CarteiraService;
import com.picpay.carteiradigital.services.HistoricoService;
import com.picpay.carteiradigital.services.UsuarioService;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
public abstract class ApplicationConfigIT {

	@SpyBean
	@Autowired
	protected UsuarioRepository usuarioRepository;

	@Autowired
	protected UsuarioService usuarioService;
	
	@SpyBean
	@Autowired
	protected HistoricoRepository historicoRepository;

	@Autowired
	protected HistoricoService historicoService;
	
	@SpyBean
	@Autowired
	protected CarteiraRepository carteiraRepository;

	@Autowired
	protected CarteiraService carteiraService;
}
