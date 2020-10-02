package com.canemreayar.foreignexchange.dto.exchange;

import io.swagger.annotations.ApiParam;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class GetExchangeRateRequestDto {

    @ApiParam(
            name =  "symbols",
            type = "String",
            value = "Currencies for getting exchange rate",
            example = "GBP,USD")
    private String symbols;

    @ApiParam(
            name =  "base",
            type = "String",
            value = "Base currency for getting exchange rate",
            example = "GBP")
    private String base;
}
