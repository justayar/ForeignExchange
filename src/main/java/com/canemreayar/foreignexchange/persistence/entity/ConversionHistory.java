package com.canemreayar.foreignexchange.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Getter
@Setter
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String baseSymbol;

    private String quoteSymbol;

    private BigDecimal rate;

    private BigDecimal sourceAmount;

    private BigDecimal targetAmount;

    private String transactionDate;

}
