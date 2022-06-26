package com.publicis.sapient.creditcard.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import lombok.Data;

import java.util.List;

/**
 * This class is api payload class
 * Response structure would be :
 *     [
 *       {
 *            "id": 1,
 *            "name": "ssssss",
 *            "cardNumber": "1232321",
 *            "balance": "0",
 *            "limit": "2132312",
 *            "createdDateTime": "2022-06-26T12:52:25.462873"
 *        }
 *      ]
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiPayload {
    private List<CreditCardDetail> cardList;
}
