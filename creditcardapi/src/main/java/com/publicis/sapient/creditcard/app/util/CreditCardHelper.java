package com.publicis.sapient.creditcard.app.util;

import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import com.publicis.sapient.creditcard.app.model.ApiPayload;
import com.publicis.sapient.creditcard.app.model.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This is helper class for credit card api.
 * This class contain method used throughout application
 */
public class CreditCardHelper {

    public static ApiResponse getApiResponse(HttpStatus httpStatus, String message, List<CreditCardDetail> creditCardDetails) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setStatus(String.valueOf(httpStatus.value()));
        if(creditCardDetails != null && !creditCardDetails.isEmpty()) {
            ApiPayload apiPayload = new ApiPayload();
            apiPayload.setCardList(creditCardDetails);
            apiResponse.setData(apiPayload);
        }
        return  apiResponse;
    }
}
