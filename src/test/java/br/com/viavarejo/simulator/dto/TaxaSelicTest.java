package br.com.viavarejo.simulator.dto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TaxaSelicTest {
	
	private TaxaSelic taxaSelic;
	
	@Before
	public void setUp() {
		this.taxaSelic = new TaxaSelic();
	}
	
	@Test
	public void deveCalculaTaxaMensalAcumulada() throws Exception {
		
		TaxaSelic t1 = new TaxaSelic();
		TaxaSelic t2 = new TaxaSelic();
		TaxaSelic t3 = new TaxaSelic();
		
		t1.setValor(new BigDecimal("0.11"));
		t2.setValor(new BigDecimal("0.22"));
		t3.setValor(new BigDecimal("0.33"));
		
		assertEquals(new BigDecimal("0.66"), taxaSelic.calculaTaxaMensalAcumulada(Arrays.asList(t1,t2,t3)));  
		
	}

}
