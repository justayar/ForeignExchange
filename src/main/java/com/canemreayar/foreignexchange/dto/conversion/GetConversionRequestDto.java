package com.canemreayar.foreignexchange.dto.conversion;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class GetConversionRequestDto {

    @Size(min=3, max=3, message = "Currency symbol size must be 3 characters!")
    @ApiModelProperty(notes = "Source currency symbol", example = "EUR")
    private String sourceCurrency;

    @Size(min=3, max=3,  message = "Currency symbol size must be 3 characters!")
    @ApiModelProperty(notes = "Target currency symbol", example = "GBP")
    private String targetCurrency;

    @ApiModelProperty(notes = "Source amount to converted", example = "100")
    private BigDecimal sourceAmount;
}
