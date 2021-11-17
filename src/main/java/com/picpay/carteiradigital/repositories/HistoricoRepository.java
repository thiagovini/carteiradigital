package com.picpay.carteiradigital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.carteiradigital.entities.Historico;
import java.lang.String;

public interface HistoricoRepository extends JpaRepository<Historico, String>{
	
	List<Historico> findByUsuarioId(String usuarioid);

}
