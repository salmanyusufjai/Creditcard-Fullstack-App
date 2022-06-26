package com.publicis.sapient.creditcard.app.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is custom Luhn10 Number validation class.
 */
public class CreditCardLuhn10NumberValidation implements ConstraintValidator<IsLuhn10Number,String> {


    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        {

            try {
                List<Integer> cardIntArray = cardNumber.chars().mapToObj(c ->
                        (char)c).map(c->Integer.parseInt(c+"")).collect(Collectors.toList());


                for(int i=cardIntArray.size()-2;i>=0;i=i-2)
                {
                    int num = cardIntArray.get(i);
                    num = num * 2;
                    if(num>9)
                    {
                        num = num%10 + num/10;
                    }
                    cardIntArray.set(i,num);
                }

                int sum =  cardIntArray.stream().mapToInt(Integer::intValue).sum();

                if(sum%10==0)
                {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            return false;

        }
    }

}
