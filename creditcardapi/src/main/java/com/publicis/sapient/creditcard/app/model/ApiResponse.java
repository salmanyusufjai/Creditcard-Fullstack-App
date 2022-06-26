package com.publicis.sapient.creditcard.app.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class is the api response class for credit card api
 * Response structure for this
 * {
 *     "timestamp": "2022-06-26T12:52:39.9754668",
 *     "status": "200",
 *     "message": "Credit card list fetched successfully",
 *     "data": {
 *         "cardList": [
 *             {
 *                 "id": 1,
 *                 "name": "ssssss",
 *                 "cardNumber": "1232321",
 *                 "balance": "0",
 *                 "limit": "2132312",
 *                 "createdDateTime": "2022-06-26T12:52:25.462873"
 *             }
 *         ]
 *     }
 * }
 */
@Data
public class ApiResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String status;
    private String message;
    private ApiPayload data;
}
