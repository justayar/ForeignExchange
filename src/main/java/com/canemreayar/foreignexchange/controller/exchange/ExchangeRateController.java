package com.canemreayar.foreignexchange.controller.exchange;

import com.canemreayar.foreignexchange.dto.exchange.ExchangeRateDto;
import com.canemreayar.foreignexchange.dto.exchange.GetExchangeRateRequestDto;
import com.canemreayar.foreignexchange.service.RatesApiService;
import com.canemreayar.foreignexchange.validation.ValidCurrencyPair;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/exchange")
@Validated
public class ExchangeRateController {

    @Autowired
    private RatesApiService ratesApiService;


    @GetMapping(value="/getExchangeRates")
    @ApiOperation(value="Get Exchange Rates", notes = "Get all exchange rates from provider service with base currency")
    public ResponseEntity<ExchangeRateDto> getExchangeRates(){



        return ResponseEntity.ok(ratesApiService.getExchangeRates(new GetExchangeRateRequestDto()));
    }


    @GetMapping(value="/getExchangeRates/{base}/{symbols}",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Get Exchange Rate of Specific Currencies with Specific Base", notes="Get exchange rate of specific currencies by currency symbols for specific base.")
    public ResponseEntity<ExchangeRateDto> getExchangeRatesByBaseAndSymbols(GetExchangeRateRequestDto exchangeRateRequestDto){
        return ResponseEntity.ok(ratesApiService.getExchangeRates(exchangeRateRequestDto));
    }



    @GetMapping(value="/getExchangeRateFor",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Get Exchange Rate of Currency Pair", notes="Get exchange rate of currency pair.")
    public ResponseEntity<ExchangeRateDto> getExchangeRatesBySymbols(@ApiParam(
                                                                        name =  "currencyPair",
                                                                        type = "String",
                                                                        value = "Currency pair which is formatted as base_currency_symbol,quote_currency_symbol",
                                                                        example = "GBP,USD",
                                                                        required = true) @RequestParam("currencyPair")  @ValidCurrencyPair String currencyPair){

        GetExchangeRateRequestDto exchangeRateRequestDto = new GetExchangeRateRequestDto();
        exchangeRateRequestDto.setBase(currencyPair.substring(0,3));
        exchangeRateRequestDto.setSymbols(currencyPair.substring(4));
        return ResponseEntity.ok(ratesApiService.getExchangeRates(exchangeRateRequestDto));
    }

}
