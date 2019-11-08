package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxaSelic {

	private BigDecimal valor;
	
	
	public BigDecimal calculaTaxaMensalAcumulada(final List<TaxaSelic> selicList) throws Exception {
		return selicList.stream()
					.map(t -> t.getValor())
					.reduce(BigDecimal::add)
					.orElse(BigDecimal.ZERO)
					.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
}
