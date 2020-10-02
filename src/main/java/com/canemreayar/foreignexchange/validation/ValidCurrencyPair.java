package com.canemreayar.foreignexchange.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyPairValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyPair {
    String message() default "Invalid currency pair. Correct format is base_currency_symbol/quote_currency_symbol.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

