package com.publicis.sapient.creditcard.app.util;

/**
 * Application wise constant would be server by this class
 */
public class CreditCardConstant {

    public static final String EMPTY_NAME = "Card name should not be empty";
    public static final String INVALID_CARD_NUMBER = "Card Number should not be empty or more then 19 characters";

    public static final String CREDIT_CARD_ONLY_NUMBER = "Card number should be valid number between  0 to 9  and must be valid Luhn10 number";
    public static final String CREDIT_CARD_NUMBER_NOT_LUHN =  "Credit card number is not valid Luhn10 number";
    public static final String INVALID_CARD_LIMIT=  "Card limit should not be empty and should be valid number between 0 to 9";
    public static final String CARD_ADDED_SUCCESSFULLY =  "Card Detail save successfully";
    public static final String CARD_LIST_FETCH_SUCCESSFULLY = "Credit card list fetched successfully";

}
