package com.publicis.sapient.creditcard;

import com.publicis.sapient.creditcard.app.CreditCardApplication;
import com.publicis.sapient.creditcard.app.dao.CreditCardDao;
import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import com.publicis.sapient.creditcard.app.util.CreditCardConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

/**
 * Credit card application credit card dao and entity validation unit test class
 */
@SpringBootTest(classes = {CreditCardApplication.class})
public class CreditCardDetailsFieldValidationAndDaoTests {

    @Autowired
    CreditCardDao creditCardDao;


    @Test
    void whenCardNameEmptyThrowsException() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setCardNumber("13265465");
        creditCardDetail.setBalance("0");
        creditCardDetail.setLimit("500");

        ConstraintViolationException  e = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            creditCardDao.save(creditCardDetail);
        });
        Assertions.assertTrue(e.getConstraintViolations().stream().
                anyMatch(s -> s.getMessage().equals(CreditCardConstant.EMPTY_NAME)));
    }

    @Test
    void whenCardNumberEmptyThrowsException() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("test");
        creditCardDetail.setBalance("0");
        creditCardDetail.setLimit("500");

        ConstraintViolationException e = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            creditCardDao.save(creditCardDetail);
        });
        Assertions.assertTrue(e.getConstraintViolations().stream().
                anyMatch(s -> s.getMessage().equals(CreditCardConstant.INVALID_CARD_NUMBER)));

    }

    @Test
    void whenCardLimitEmptyThrowsException() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("test");
        creditCardDetail.setCardNumber("1232321");
        creditCardDetail.setBalance("0");

        ConstraintViolationException e = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            creditCardDao.save(creditCardDetail);
        });
        Assertions.assertTrue(e.getConstraintViolations().stream().
                anyMatch(s -> s.getMessage().equals(CreditCardConstant.INVALID_CARD_LIMIT)));
        }

    @Test
    void whenCardBalanceEmptyThrowsException() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("test");
        creditCardDetail.setCardNumber("1232321");
        creditCardDetail.setLimit("4645654");
        creditCardDetail.setBalance(null);

       Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            creditCardDao.save(creditCardDetail);
        });
    }

    @Test
    void whenCardCardNotValidLuhn10NumberThrowsException() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("test");
        creditCardDetail.setCardNumber("15643131");
        creditCardDetail.setLimit("4645654");

        ConstraintViolationException e = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            creditCardDao.save(creditCardDetail);
        });
        Assertions.assertTrue(e.getConstraintViolations().stream().
                anyMatch(s -> s.getMessage().equals(CreditCardConstant.CREDIT_CARD_NUMBER_NOT_LUHN)));
    }

    @Test
    void whenProperCardDetail_SaveSuccessfully() {
        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("test");
        creditCardDetail.setCardNumber("1232321");
        creditCardDetail.setLimit("4645654");
        creditCardDetail.setBalance("0");
        creditCardDetail =   creditCardDao.save(creditCardDetail);
        Assertions.assertTrue(creditCardDetail.getId() >  0);
    }

}
