package com.publicis.sapient.creditcard.app.controller;

import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import com.publicis.sapient.creditcard.app.model.ApiResponse;
import com.publicis.sapient.creditcard.app.service.CreditCardService;
import com.publicis.sapient.creditcard.app.util.CreditCardHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.CARD_ADDED_SUCCESSFULLY;
import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.CARD_LIST_FETCH_SUCCESSFULLY;

/**
 * This is controller class for Credit card CRUD operation API
 */
@RestController
@RequestMapping("creditcard/api")
public class CreditCardController {

	@Autowired
	CreditCardService creditCardService;

	/**
	 * This controller method is used to add credit card details
	 * @param creditCardDetail
	 * @return ResponseEntity of ApiResponse
	 */
	@PostMapping(path = "/v1/addCard",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Validated
	public ResponseEntity<ApiResponse> addCard( @Valid
			@RequestBody final CreditCardDetail creditCardDetail) {

		creditCardService.addCardDetails(creditCardDetail);
		return new ResponseEntity<ApiResponse>(CreditCardHelper.getApiResponse(HttpStatus.OK,CARD_ADDED_SUCCESSFULLY,null), HttpStatus.OK);
	}

	/**
	 * This method is used to get all available credit card list
	 * @return ResponseEntity with api response object contain credit card list in data
	 */
	@GetMapping(path = "/v1/getAllCards",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getAllCards() {
		return new ResponseEntity<ApiResponse>(CreditCardHelper.getApiResponse(HttpStatus.OK,CARD_LIST_FETCH_SUCCESSFULLY,creditCardService.getAllCards()), HttpStatus.OK);
	}
}
