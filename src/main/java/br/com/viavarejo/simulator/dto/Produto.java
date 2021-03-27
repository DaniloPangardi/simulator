package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
	
	@NotNull(message = "* Informe o código do Produto!")
	private Long codigo;
	
	@NotBlank(message = "* Informe o nome do Produto!")
	private String nome;
	
	@NotNull(message = "* Informe o valor do Produto!")
	@DecimalMin(value="1.0", message="* Informe um valor maior que 0")
	private BigDecimal valor;
		
}
