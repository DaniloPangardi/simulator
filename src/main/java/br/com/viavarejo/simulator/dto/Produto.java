package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
	
	private Long codigo;
	private String nome;
	private BigDecimal valor;
		
}
