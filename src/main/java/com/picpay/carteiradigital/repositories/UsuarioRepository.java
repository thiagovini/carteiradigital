package com.picpay.carteiradigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.carteiradigital.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	

}
