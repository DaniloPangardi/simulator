package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondicaoPagamento {

	@NotNull(message = "* Informe o valor da entrada!")
	@DecimalMin(value="1.00", message="* Informe o valor da entrada!")
	private BigDecimal valorEntrada;
	
	@NotNull(message = "* Informe a quantidade de parcelas!")
	@DecimalMin(value="1", message="* Informe a quantidade de parcelas!")
	private Integer qtdeParcelas;
}
