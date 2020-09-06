package com.senac.assister.payment.service;

import com.senac.assister.backend.domain.entity.CreditCard;

public interface PaymentService {

    /**
     * Return the token on type String to validated card.
     *
     * @param creditCard
     * @return
     */
    public String createCreditCard(CreditCard creditCard);

    public void authorize();
}
