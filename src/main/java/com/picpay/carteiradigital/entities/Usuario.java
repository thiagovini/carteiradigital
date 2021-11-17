package com.picpay.carteiradigital.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	private String id;
	private String nome;
	private String sobrenome;
	private String cpf;

}
