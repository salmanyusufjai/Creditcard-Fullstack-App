package com.publicis.sapient.creditcard;

import com.publicis.sapient.creditcard.app.CreditCardApplication;
import com.publicis.sapient.creditcard.app.Exception.CreditCardValidationException;
import com.publicis.sapient.creditcard.app.dao.CreditCardDao;
import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import com.publicis.sapient.creditcard.app.service.CreditCardService;
import com.publicis.sapient.creditcard.app.util.CreditCardHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

/**
 * Credit card application Credit card service unit test class
 */
@SpringBootTest(classes = {CreditCardApplication.class})
public class CreditCardServiceTests {
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private CreditCardDao creditCardDao;

    @Test
    public void testAddCreditCardDetails() {

        CreditCardDetail creditCardDetail = new CreditCardDetail();
        creditCardDetail.setName("Test");
        creditCardDetail.setCardNumber("1358954993914435");
        creditCardDetail.setBalance("200");
        creditCardDetail.setLimit("500");

        CreditCardDetail creditCardDetail1 = creditCardService.addCardDetails(creditCardDetail);
        Assertions.assertTrue( creditCardDetail1.getId()>0);
    }

    @Test
    public void testGetCreditCardDetails() {
        testAddCreditCardDetails();
        Assertions.assertTrue( creditCardService.getAllCards().size() > 0);
    }
}
