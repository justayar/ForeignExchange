package com.canemreayar.foreignexchange.service;

import com.canemreayar.foreignexchange.config.ApplicationProperties;
import com.canemreayar.foreignexchange.dto.exchange.ExchangeRateDto;
import com.canemreayar.foreignexchange.dto.exchange.GetExchangeRateRequestDto;
import com.canemreayar.foreignexchange.exception.RateServiceNotAccessibleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Log4j2
@RequiredArgsConstructor
public class RatesApiServiceImpl implements RatesApiService {

    static final String GETEXCHANGERATES_SERVICE_COMMAND = "GetExchangeRates_Service";

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    @CircuitBreaker(name = GETEXCHANGERATES_SERVICE_COMMAND, fallbackMethod="getExchangeRatesFallbackMethod")
    public ExchangeRateDto getExchangeRates(GetExchangeRateRequestDto exchangeRateRequestDto) {
        log.info("[getExchangeRates()] Exchange rates api will be called.");

        URI apiUrl= UriComponentsBuilder.fromUriString(applicationProperties.getRatesApiProvider().getExchangeRateService())
                .queryParam("base", exchangeRateRequestDto.getBase())
                .queryParam("symbols", exchangeRateRequestDto.getSymbols())
                .build()
                .encode()
                .toUri();


        try {
            return restTemplate.getForObject(apiUrl, ExchangeRateDto.class);

        } catch(HttpStatusCodeException ex){
            log.info("[getExchangeRates()] Exchange rates api -- Request is {}, Exception Response Body is {}",exchangeRateRequestDto.toString(),
                    ex.getResponseBodyAsString());
            return null;
        } catch(ResourceAccessException ex){
            throw new RateServiceNotAccessibleException();
        } catch(RestClientException e){
            log.info("[getExchangeRates()] Exchange rates api -- Request is {}, Exception Message is {}",exchangeRateRequestDto.toString(),e.getMessage());
            return null;
        }
    }

}
