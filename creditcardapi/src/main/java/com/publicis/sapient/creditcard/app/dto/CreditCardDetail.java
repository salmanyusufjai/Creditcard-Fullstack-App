package com.publicis.sapient.creditcard.app.dto;

import com.publicis.sapient.creditcard.app.config.IsLuhn10Number;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.*;

/**
 * This is entity class for Credit card api.
 * This class is mapped with creditcarddetail table
 * Also this class will use to map api request and response with card details
 * All field level validation applied on this class so that only valid value should
 * persist in DB.
 */
@Entity
@Table(name = "CREDITCARDDETAIL")
@Data
public class CreditCardDetail implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    @NotEmpty(message = EMPTY_NAME)
    private String name;

    @Column(name = "CARDNUMBER", nullable = false)
    @IsLuhn10Number
    @Size(min=2,max=19, message = INVALID_CARD_NUMBER)
    @Pattern(regexp = "^\\d+$", message = CREDIT_CARD_ONLY_NUMBER)
    @NotEmpty(message = INVALID_CARD_NUMBER)
    private String cardNumber;

    @Column(name = "BALANCE", nullable = false)
    private String balance = "0";

    @Column(name = "CARDLIMIT", nullable = false)
    @NotEmpty(message = INVALID_CARD_LIMIT)
    @Size(min=1, message = INVALID_CARD_LIMIT)
    @Pattern(regexp = "^\\d+$", message = INVALID_CARD_LIMIT)
    private String limit;

    @Column(name = "CREATEDDATETIME", nullable = false)
    private LocalDateTime createdDateTime = LocalDateTime.now();

}
