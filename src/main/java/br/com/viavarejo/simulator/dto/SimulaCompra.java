package br.com.viavarejo.simulator.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulaCompra {
	
	private Produto produto;
	private CondicaoPagamento condicaoPagamento;
	
	public BigDecimal calculaValorLiquido() throws Exception {
		final BigDecimal valorProduto = Optional.ofNullable(produto.getValor())
			       .orElseThrow(() -> new NullPointerException("Informe o valor do produto!"));
		
		final BigDecimal valorEntrada = Optional.ofNullable(condicaoPagamento.getValorEntrada())
				                                .orElse(BigDecimal.ZERO);

		if(valorProduto.compareTo(valorEntrada) > 0) {
			return valorProduto.subtract(valorEntrada);
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
