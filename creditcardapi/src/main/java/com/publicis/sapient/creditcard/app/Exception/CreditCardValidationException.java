package com.publicis.sapient.creditcard.app.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is Custom runtime exception specific to Credit card application
 * This exception will throw when know validation occurred  in the application
 */
public class CreditCardValidationException extends RuntimeException {
    @Getter
    private HttpStatus statusCode;
    @Getter
    private final String message;

    public CreditCardValidationException(HttpStatus httpStatus,String message) {
        this.statusCode = httpStatus;
        this.message = message;
    }
}
