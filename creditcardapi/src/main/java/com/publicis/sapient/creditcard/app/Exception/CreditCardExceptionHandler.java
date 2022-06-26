package com.publicis.sapient.creditcard.app.Exception;

import com.publicis.sapient.creditcard.app.util.CreditCardHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

/**
 * This class responsible to handle all exception related
 * to credit card API
 */
@ControllerAdvice
public class CreditCardExceptionHandler {

    /**
     * This method will handle all custom exception throw by credit class application
     * @param creditCardValidationException
     * @return response entity object with status code and message
     */
    @ExceptionHandler(CreditCardValidationException.class)
    public ResponseEntity<Object> handleCompanySearchException(CreditCardValidationException
                                                                       creditCardValidationException) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(CreditCardHelper.getApiResponse(
                creditCardValidationException.getStatusCode(),creditCardValidationException.getMessage(),
                null),headers,
                creditCardValidationException.getStatusCode());
    }

    /**
     * This method will handle all http servlet exception throw by credit class application
     * @param servletException
     * @return response entity object with status code and message.
     */
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Object> handleMissingRequestHeaderException(ServletException servletException) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(CreditCardHelper.getApiResponse(HttpStatus.BAD_REQUEST
                ,servletException.getMessage(),null),headers,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * This method will handle all http servlet exception throw by credit class application
     * @param mrException MethodArgumentNotValidException will throw at controller level when invalid
     * parameter supply in post request
     * @return response entity object with status code and message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException mrException) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(CreditCardHelper.getApiResponse(HttpStatus.BAD_REQUEST,
                mrException.getAllErrors().get(0).getDefaultMessage(),null),headers,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * This method will handle all generic exception not handle at application level
     * @param exception generic exception
     * @return response entity object with status code and message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(CreditCardHelper.getApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage(),null),headers,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
