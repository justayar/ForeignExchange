package com.canemreayar.foreignexchange.dto.exchange;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ExchangeRateDto {

    @ApiModelProperty(notes = "Base currency for exchange rate service", example = "EUR")
    private String base;

    @ApiModelProperty(notes = "Base currency for exchange rate service", example = "{\n" +
            "    \"GBP\": 0.90963,\n" +
            "    \"HKD\": 9.0691,\n" +
            "    \"IDR\": 17419\n" +
            "  }")
    private Map<String, BigDecimal> rates;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(notes = "Date for latest day for exchange rates calculated", example = "2020-09-29")
    private LocalDate date;
}
