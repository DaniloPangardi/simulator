package br.com.viavarejo.simulator.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viavarejo.simulator.dto.CondicaoPagamento;
import br.com.viavarejo.simulator.dto.Response;
import br.com.viavarejo.simulator.dto.SimulaCompra;
import br.com.viavarejo.simulator.exception.NotFoundException;

@Service
public class SimulaCompraService {

	@Autowired
	private BancoCentralService bancoCentralService;
	
	public List<Response> simular(final SimulaCompra request) throws Exception {
		
        if (request == null || request.getProduto() == null
        		            || request.getCondicaoPagamento() == null) {
        	throw new NotFoundException("Verifique os valores informados.");
		} 
        
    
        final BigDecimal valorLiquido = request.calculaValorLiquido();

        final CondicaoPagamento condicaoPagamento = request.getCondicaoPagamento();

        final int quantidadeParcelas = Optional.ofNullable(condicaoPagamento.getQtdeParcelas())
				                               .orElseThrow(() -> new NullPointerException("Informe a quantidade de parcelas!"));
		
		if (quantidadeParcelas < 1){
			throw new IllegalArgumentException("Informe um valor inteiro maior que 0 para a quantidade de parcelas!");
		}
		
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
		
		final List<Response> responseList = new ArrayList<Response>();
		
		for(int i=1; i <= quantidadeParcelas; i++) {
			Response response = new Response();
			response.setNumeroParcela(i);
			response.setValor(valorParcela);
			response.setTaxaJurosAoMes(taxaMensalAcumulada);
			responseList.add(response);
			response = null;
		}
		
		return responseList;
	}
}
