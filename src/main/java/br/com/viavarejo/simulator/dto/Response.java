package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private Integer numeroParcela;
	private BigDecimal valor;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal taxaJurosAoMes;

}
