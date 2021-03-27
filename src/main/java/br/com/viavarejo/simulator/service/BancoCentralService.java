package br.com.viavarejo.simulator.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.viavarejo.simulator.dto.TaxaSelic;

@Service
public class BancoCentralService {

	private TaxaSelicService taxaSelicService;

	public BancoCentralService(TaxaSelicService taxaSelicService) {
		this.taxaSelicService = taxaSelicService;
	}

	public BigDecimal findSelicMensalAcumulada() throws Exception {
		final List<TaxaSelic> selicList = taxaSelicService.getTaxaSeliciLst();
		return taxaSelicService.calculaTaxaMensalAcumulada(selicList);
	}

}