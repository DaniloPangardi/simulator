package br.com.viavarejo.simulator.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.viavarejo.simulator.dto.Response;
import br.com.viavarejo.simulator.dto.SimulaCompra;

@Service
public class SimulaCompraService {

	private BancoCentralService bancoCentralService;
	
	public SimulaCompraService(BancoCentralService bancoCentralService) {
		this.bancoCentralService = bancoCentralService;
	}
	
	public List<Response> simular(final SimulaCompra request) throws Exception {
		
        final BigDecimal valorLiquido = request.calculaValorLiquido();

        final int quantidadeParcelas = request.getCondicaoPagamento().getQtdeParcelas();
		
		BigDecimal valorParcela = request.calculaValorDaParcela(valorLiquido, quantidadeParcelas);
		
		BigDecimal taxaMensalAcumulada = null;
				
		if(quantidadeParcelas > 6) {
			taxaMensalAcumulada = bancoCentralService.findSelicMensalAcumulada();
			valorParcela = request.calculaValorDaParcelaAcrescida(valorParcela, taxaMensalAcumulada);
		}
		
		return getResponse(quantidadeParcelas, valorParcela, taxaMensalAcumulada);
		
	}

	private List<Response> getResponse(final int quantidadeParcelas, 
			                           final BigDecimal valorParcela,
			                           final BigDecimal taxaMensalAcumulada) throws Exception {
		
		final List<Response> responseList = new ArrayList<>();
	
		for(int i=1; i <= quantidadeParcelas; i++) {
			responseList.add(buidResponse(valorParcela, taxaMensalAcumulada, i));
		}
		
		return responseList;
	}

	private Response buidResponse(final BigDecimal valorParcela, 
			                      final BigDecimal taxaMensalAcumulada, int numeroParcela) throws Exception {
		return Response.builder()
					   .numeroParcela(numeroParcela)
					   .valor(valorParcela)
					   .taxaJurosAoMes(taxaMensalAcumulada)
					   .build();
	}
}
