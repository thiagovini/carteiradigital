package com.picpay.carteiradigital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.carteiradigital.entities.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, String>{
	
	Optional<Carteira> findByNumeroConta(String numeroConta);
	
	Optional<Carteira> findByUsuarioId(String usuarioId);
	
}
