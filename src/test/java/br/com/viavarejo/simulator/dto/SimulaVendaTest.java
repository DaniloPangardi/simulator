package br.com.viavarejo.simulator.dto;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.viavarejo.simulator.dto.CondicaoPagamento;
import br.com.viavarejo.simulator.dto.Produto;
import br.com.viavarejo.simulator.dto.SimulaCompra;

public class SimulaVendaTest {
	
	private SimulaCompra simulaVenda;
	
	@Before
	public void setup() {
		this.simulaVenda = new SimulaCompra();
	}
	
	@Test
	public void deveCalcularValorLiquido() throws Exception {
		Produto produto = new Produto();
		produto.setValor(new BigDecimal("120.00"));
		
		CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
		condicaoPagamento.setValorEntrada(new BigDecimal("60.00"));
		
		simulaVenda.setProduto(produto);
		simulaVenda.setCondicaoPagamento(condicaoPagamento);
		
		assertEquals(new BigDecimal("60.00"), simulaVenda.calculaValorLiquido());
	}
	
	@Test
	public void deveCalcularValorDaParcela() throws Exception {
		assertEquals(new BigDecimal("10.00"), simulaVenda.calculaValorDaParcela(new BigDecimal("60.00"), 6));
	}
	
	@Test
	public void deveCalcularValorDaParcelaAcrescida() throws Exception {
		assertEquals(new BigDecimal("1.10"), simulaVenda.calculaValorDaParcelaAcrescida(new BigDecimal("1.00"), new BigDecimal("10.00")));
	}

	
}
