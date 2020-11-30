package com.senac.assister.payment.service;

import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.security.Hash;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * Token is the MD5 of Card number, Holder name, Expiration date, Brand and Created_at
     *
     * @param creditCard
     * @return
     */
    @Override
    public String tokenization(CreditCard creditCard) {
        String paymentInfo = creditCard.getHolderNumber() +
                creditCard.getHolderName() +
                creditCard.getExpirationDate() +
                creditCard.getBrand().toString() +
                creditCard.getCreatedAt().toString();

        return Hash.convertToMd5(paymentInfo);
    }

    @Override
    public void authorize(Charge charge) {

    }
}
