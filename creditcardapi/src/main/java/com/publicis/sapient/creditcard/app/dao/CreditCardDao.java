package com.publicis.sapient.creditcard.app.dao;

import com.publicis.sapient.creditcard.app.dto.CreditCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is responsible for DAO related operation for credit card app
 */
@Repository
public interface CreditCardDao extends JpaRepository<CreditCardDetail, Long> {
}
