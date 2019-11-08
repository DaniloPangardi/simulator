package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondicaoPagamento {

	private BigDecimal valorEntrada;
	private Integer qtdeParcelas;
}
