package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulaCompra {
	
	@Valid
	@NotNull(message="* Informe um Produto!")
	private Produto produto;
	
	@Valid
	@NotNull(message="* Informe a condição de pagamento!")
	private CondicaoPagamento condicaoPagamento;
	
	public BigDecimal calculaValorLiquido() throws Exception {

		if(produto.getValor().compareTo(condicaoPagamento.getValorEntrada()) > 0) {
			return produto.getValor().subtract(condicaoPagamento.getValorEntrada());
		}
		throw new IllegalArgumentException("O valor da entrada deve ser menor que o valor do produto!");
	}
	
	public BigDecimal calculaValorDaParcela(final BigDecimal valorLiquido, final int quantidadeParcelas) throws Exception {
		return valorLiquido.divide(new BigDecimal(quantidadeParcelas), BigDecimal.ROUND_HALF_EVEN) .setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal calculaValorDaParcelaAcrescida(final BigDecimal valorParcela, final BigDecimal taxaMensalAcumulada) throws Exception {
		return valorParcela.add(valorParcela.multiply(taxaMensalAcumulada.divide(new BigDecimal("100"))))
				                                                         .setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	

	
}
