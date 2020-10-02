package com.canemreayar.foreignexchange.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ForeignExchangeApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ForeignExchangeApiError() {
        timestamp = LocalDateTime.now();
    }

    ForeignExchangeApiError(HttpStatus status) {
        this();
        this.status = status;
    }

}