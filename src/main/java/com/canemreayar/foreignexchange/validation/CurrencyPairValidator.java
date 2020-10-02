package com.canemreayar.foreignexchange.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyPairValidator implements
        ConstraintValidator<ValidCurrencyPair, String> {

    private static final int STANDARD_CURRENCY_NUMBER_OF_LETTER = 3;

    private static final int STANDARD_CURRENCY_PAIR_NUMBER_OF_LETTERS = STANDARD_CURRENCY_NUMBER_OF_LETTER * 2 + 1;

    private static final char CURRENCY_PAIR_SEPARATOR_CHARACTER = ',';


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isValidCurrencyPairLength(s) &&
                isValidSeparatorCharacterForCurrencyPair(s);
    }

    private boolean isValidSeparatorCharacterForCurrencyPair(String currencyPair){

        return CURRENCY_PAIR_SEPARATOR_CHARACTER == currencyPair.charAt(currencyPair.length()/2);

    }

    private boolean isValidCurrencyPairLength(String currencyPair){
        return currencyPair.length() == STANDARD_CURRENCY_PAIR_NUMBER_OF_LETTERS;
    }


}
