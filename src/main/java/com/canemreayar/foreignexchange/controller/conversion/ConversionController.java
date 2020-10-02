package com.canemreayar.foreignexchange.controller.conversion;

import com.canemreayar.foreignexchange.dto.conversion.GetConversionListRequestDto;
import com.canemreayar.foreignexchange.dto.conversion.GetConversionRequestDto;
import com.canemreayar.foreignexchange.dto.exchange.ExchangeRateDto;
import com.canemreayar.foreignexchange.dto.exchange.GetExchangeRateRequestDto;
import com.canemreayar.foreignexchange.mapper.ConversionHistoryMapper;
import com.canemreayar.foreignexchange.persistence.entity.ConversionHistory;
import com.canemreayar.foreignexchange.service.RatesApiService;
import com.canemreayar.foreignexchange.service.ConversionHistoryCrudService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/conversion")
public class ConversionController {

    @Autowired
    private RatesApiService ratesApiService;

    @Autowired
    private ConversionHistoryCrudService conversionHistoryCrudService;

    private final ConversionHistoryMapper conversionHistoryMapper;

    @Value("${transactionDateFormat}")
    private String TRANSACTION_DATE_FORMAT;


    @GetMapping(value="/getConversion",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Calculate the amount after conversion", notes="Get calculated conversion amount with quote symbol")
    public ResponseEntity<ConversionHistory> getConversion(GetConversionRequestDto conversionRequestDto){


        GetExchangeRateRequestDto exchangeRateRequestDto = this.conversionHistoryMapper.map(conversionRequestDto);

        ExchangeRateDto exchangeRatesDto = ratesApiService.getExchangeRates(exchangeRateRequestDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TRANSACTION_DATE_FORMAT);
        String transactionDate = LocalDate.now().format(formatter);

        ConversionHistory conversionHistory = this.conversionHistoryMapper.map(exchangeRatesDto,conversionRequestDto,transactionDate);

        ConversionHistory crudServiceConversionHistory = conversionHistoryCrudService.createConversionHistory(conversionHistory);

        return ResponseEntity.ok(crudServiceConversionHistory);
    }

    @GetMapping(value="/getConversionsList",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Get conversions from conversion history", notes="Get conversions from conversion history with transaction id or transaction date.")
    public ResponseEntity<List<ConversionHistory>> getConversionsList(GetConversionListRequestDto conversionListRequestDto){

        ConversionHistory conversionHistory = this.conversionHistoryMapper.map(conversionListRequestDto);

        return ResponseEntity.ok(conversionHistoryCrudService.getConversionsByTransactionIdOrDate(conversionHistory));
    }
}
