package com.canemreayar.foreignexchange.dto.conversion;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class GetConversionListRequestDto {

    @ApiModelProperty(notes = "Transaction id of conversion", example = "1")
    private long transactionId;

    @ApiModelProperty(notes = "Transaction date when the conversion was done", example = "2020-09-29")
    private String transactionDate;
}
