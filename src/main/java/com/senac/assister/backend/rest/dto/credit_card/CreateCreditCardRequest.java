package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCreditCardRequest {
    private static ModelMapper mapper = new ModelMapper();

    @NotNull(message = "customerId field must be sent.")
    private UUID customerId;

    @NotNull(message = "holderNumber field must be sent.")
    @Size(min = 16, max = 20, message = "holderNumber must be between 16 and 20 characters")
    private String holderNumber;

    @NotNull(message = "holderName field must be sent.")
    @Size(min = 2, max = 26, message = "holderName must be between 12 and 26 characters")
    private String holderName;

    @NotNull(message = "expirationDate field must be sent.")
    @Size(min = 1, max = 5, message = "expirationDate must be between 1 and 5 characters on format MM/YY")
    private String expirationDate;

    @NotNull(message = "credit_card_brand field must be sent.")
    private CreditCardBrand creditCardBrand;

    public static CreditCard convertToEntity(CreateCreditCardRequest request) {
        return mapper.map(request, CreditCard.class);
    }
}