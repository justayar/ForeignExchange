package com.canemreayar.foreignexchange.service;

import com.canemreayar.foreignexchange.dto.exchange.ExchangeRateDto;
import com.canemreayar.foreignexchange.dto.exchange.GetExchangeRateRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface RatesApiService{

    ExchangeRateDto getExchangeRates(GetExchangeRateRequestDto exchangeRateRequestDto);

}
