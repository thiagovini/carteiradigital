package com.picpay.carteiradigital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.carteiradigital.entities.Usuario;
import com.picpay.carteiradigital.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscaUsuarioPeloId(String id) {
		
		return usuarioRepository.findById(id).orElseThrow();
		
	}
	
}
