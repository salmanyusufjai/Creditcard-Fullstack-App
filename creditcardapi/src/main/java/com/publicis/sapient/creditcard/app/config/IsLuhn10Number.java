package com.publicis.sapient.creditcard.app.config;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.CREDIT_CARD_NUMBER_NOT_LUHN;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom annotation class for Luna10 Number validation
 * This will be used on field level annotation to
 * validate if field value is Luna10 number or not
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CreditCardLuhn10NumberValidation.class)
@Documented
public @interface IsLuhn10Number {


    String message() default CREDIT_CARD_NUMBER_NOT_LUHN;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
