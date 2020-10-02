package com.canemreayar.foreignexchange.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${conversionlist.bad.request.message}")
    private String CONVERSATION_LIST_BAD_REQUEST_MESSAGE;

    @Value("${rateservice.not.accessible.error.message}")
    private String RATESERVICE_NOT_ACCESSIBLE_ERROR_MESSAGE;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ForeignExchangeApiError(BAD_REQUEST));
    }

    private ResponseEntity<Object> buildResponseEntity(ForeignExchangeApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ConversionListRequestBadParametersException.class)
    protected ResponseEntity<Object> handleConversionListRequestBadParametersException() {
        ForeignExchangeApiError apiError = new ForeignExchangeApiError(BAD_REQUEST);
        apiError.setMessage(CONVERSATION_LIST_BAD_REQUEST_MESSAGE);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RateServiceNotAccessibleException.class)
    protected ResponseEntity<Object> handleRateServiceNotAccessibleException() {
        ForeignExchangeApiError apiError = new ForeignExchangeApiError(BAD_REQUEST);
        apiError.setMessage(RATESERVICE_NOT_ACCESSIBLE_ERROR_MESSAGE);
        return buildResponseEntity(apiError);
    }
}