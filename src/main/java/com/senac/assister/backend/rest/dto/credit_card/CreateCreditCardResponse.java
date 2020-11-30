package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCreditCardResponse {
    private static ModelMapper mapper = new ModelMapper();

    private String token;

    public static CreateCreditCardResponse convertToDto(CreditCard request) {
        return mapper.map(request, CreateCreditCardResponse.class);
    }
}
