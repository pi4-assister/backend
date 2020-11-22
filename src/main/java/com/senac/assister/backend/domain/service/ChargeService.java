package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ChargeStatus;
import com.senac.assister.backend.domain.repository.ChargeRepository;
import com.senac.assister.payment.service.PaymentServiceImpl;

@org.springframework.stereotype.Service
public class ChargeService {

    private final ChargeRepository chargeRepository;
    private final PaymentServiceImpl paymentService;

    public ChargeService(ChargeRepository chargeRepository, PaymentServiceImpl paymentService) {
        this.chargeRepository = chargeRepository;
        this.paymentService = paymentService;
    }

    public Charge chargeService(Service service) {
        Charge charge = new Charge();

        charge.setAmount(service.getTotalPrice());
        charge.setService(service);

        paymentService.authorize(charge);

        charge.setStatus(ChargeStatus.PAID);

        return chargeRepository.save(charge);
    }
}