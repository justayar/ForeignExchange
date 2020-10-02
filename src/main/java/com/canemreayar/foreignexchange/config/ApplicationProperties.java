package com.canemreayar.foreignexchange.config;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApplicationProperties {

    @Valid
    public RatesApiProvider ratesApiProvider = new RatesApiProvider();

    @Getter
    @Setter
    public class RatesApiProvider{

        @NotNull
        String exchangeRateService;
        @NotNull
        String conversionRateService;


    }
}
