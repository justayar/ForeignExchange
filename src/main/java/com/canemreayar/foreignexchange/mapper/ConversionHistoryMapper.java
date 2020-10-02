package com.canemreayar.foreignexchange.mapper;

import com.canemreayar.foreignexchange.config.MapperConfiguration;
import com.canemreayar.foreignexchange.dto.conversion.GetConversionListRequestDto;
import com.canemreayar.foreignexchange.dto.conversion.GetConversionRequestDto;
import com.canemreayar.foreignexchange.dto.exchange.ExchangeRateDto;
import com.canemreayar.foreignexchange.dto.exchange.GetExchangeRateRequestDto;
import com.canemreayar.foreignexchange.persistence.entity.ConversionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(config = MapperConfiguration.class, uses = {LocalDate.class, DateTimeFormatter.class})
public interface ConversionHistoryMapper {

    @Mapping(target="baseSymbol", expression="java(exchangeRateDto.getBase())")
    @Mapping(target = "quoteSymbol", expression = "java(getConversionRequestDto.getTargetCurrency())")
    @Mapping(target = "sourceAmount", expression="java(getConversionRequestDto.getSourceAmount())")
    @Mapping(target = "targetAmount", expression="java(getConversionRequestDto.getSourceAmount().multiply(exchangeRateDto.getRates().get(getConversionRequestDto.getTargetCurrency())))")
    @Mapping(target = "transactionDate", source="currentDate")
    @Mapping(target="rate", expression = "java(exchangeRateDto.getRates().get(getConversionRequestDto.getTargetCurrency()))")
    ConversionHistory map(ExchangeRateDto exchangeRateDto, GetConversionRequestDto getConversionRequestDto,String currentDate);

    @Mapping(target="base", source="sourceCurrency")
    @Mapping(target="symbols", source="targetCurrency")
    GetExchangeRateRequestDto map(GetConversionRequestDto conversionRequestDto);

    @Mapping(target="transactionId", expression="java(getConversionListRequestDto.getTransactionId())")
    @Mapping(target="transactionDate", expression="java(getConversionListRequestDto.getTransactionDate())")
    ConversionHistory map(GetConversionListRequestDto getConversionListRequestDto);



}
