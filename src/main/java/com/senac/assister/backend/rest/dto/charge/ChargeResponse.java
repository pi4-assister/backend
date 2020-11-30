package com.senac.assister.backend.rest.dto.charge;

import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.ChargeStatus;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponse;
import com.senac.assister.backend.rest.dto.rate.RateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChargeResponse {
    private static ModelMapper mapper = new ModelMapper();


    private UUID id;

    private CreditCard creditCard;

    private ChargeStatus status;

    private double amount;

    public static ChargeResponse convertToDto(Charge request) {
        return mapper.map(request, ChargeResponse.class);
    }
}
