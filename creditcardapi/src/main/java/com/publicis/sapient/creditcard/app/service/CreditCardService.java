package com.publicis.sapient.creditcard.app.service;

import com.publicis.sapient.creditcard.app.Exception.CreditCardValidationException;
import com.publicis.sapient.creditcard.app.dao.CreditCardDao;
import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service class is used to serve Credit card save and get operation
 */
@Service
public class CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;


    /**
     * This method is used to get All credit card list
     * @return List of Credit cards
     * @throws CreditCardValidationException runtime exception if credit card list is empty
     */
    public List<CreditCardDetail> getAllCards() {
        List<CreditCardDetail> creditCardDetails = creditCardDao.findAll();
        if(creditCardDetails == null || creditCardDetails.isEmpty())
            throw new CreditCardValidationException(HttpStatus.NOT_FOUND, "No existing credit cards available");
        else
            return creditCardDetails;
    }


    /**
     * This method is use to save new credit card
     * @param creditCardDetail object
     * @return creditCardDetail object which is having generated id in it
     */
    @Transactional
    public CreditCardDetail addCardDetails(CreditCardDetail creditCardDetail) {
        return creditCardDao.save(creditCardDetail);
    }
}
