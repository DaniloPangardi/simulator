package br.com.viavarejo.simulator.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.viavarejo.simulator.dto.TaxaSelic;

@Service
public class TaxaSelicService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BancoCentralService.class);

	private static final String PATTERN_DATE = "dd/MM/yyyy";

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);

	@Value("${url.find.selic.by.startdate.and.enddate}")
	private String urlFindSelicByStartDateAndEndDate;
	
	private RestTemplate restTemplate;

	public TaxaSelicService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public List<TaxaSelic> getTaxaSeliciLst() throws HttpClientErrorException, Exception {

		final String url = MessageFormat.format(
				urlFindSelicByStartDateAndEndDate, 
				getMesPassado(),
				getHoje());

		LOGGER.info("Url: {}", url);
		return Arrays.asList(restTemplate.getForObject(url, TaxaSelic[].class));
	}

	private String getHoje() throws Exception {
		return LocalDate.now().format(formatter);
	}

	private String getMesPassado() throws Exception {
		return LocalDate.now().minusDays(30).format(formatter);
	}

	public BigDecimal calculaTaxaMensalAcumulada(List<TaxaSelic> selicList) throws Exception {
		return TaxaSelic.calculaTaxaMensalAcumulada(selicList);
	}

}
