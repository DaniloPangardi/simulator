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
public class BancoCentralService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BancoCentralService.class);
	
	private static final String PATTERN_DATE = "dd/MM/yyyy";

	
	@Value("${url.find.selic.by.startdate.and.enddate}")
	private String urlFindSelicByStartDateAndEndDate;
	
	
	public BigDecimal findSelicMensalAcumulada() throws HttpClientErrorException, Exception {
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
		
		final String startDateFormat = LocalDate.now().minusDays(30).format(formatter);
		final String endDateFormat   = LocalDate.now().format(formatter);
		

		final String urlRequest = MessageFormat.format(urlFindSelicByStartDateAndEndDate, startDateFormat, endDateFormat);
		
		LOGGER.info("Url request: {}", urlRequest);
		
		final RestTemplate restTemplate = new RestTemplate();

		final List<TaxaSelic> selicList = Arrays.asList(restTemplate.getForObject(urlRequest, TaxaSelic[].class));
		
		final TaxaSelic selic = new TaxaSelic();
		
		return selic.calculaTaxaMensalAcumulada(selicList);
			
	}


	
	
	
	
}
