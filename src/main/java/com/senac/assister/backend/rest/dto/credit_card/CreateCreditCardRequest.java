package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import com.senac.assister.backend.rest.dto.customer.UpdateCustomerRequest;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class CreateCreditCardRequest {
    private static ModelMapper mapper = new ModelMapper();

    @NotNull(message = "customerId field must be sent.")
    private UUID customerId;

    @NotNull(message = "creditCardNumber field must be sent.")
    @Size(min = 16, max = 20, message = "creditCardNumber must be between 1 and 20 characters")
    private String creditCardNumber;

    @NotNull(message = "creditCardName field must be sent.")
    @Size(min = 1, max = 255, message = "creditCardName must be between 1 and 255 characters")
    private String creditCardName;

    @NotNull(message = "expirationDate field must be sent.")
    @Size(min = 1, max = 5, message = "expirationDate must be between 1 and 5 characters on format MM/YY")
    private String expirationDate;

    @NotNull(message = "credit_card_brand field must be sent.")
    @Size(min = 1, max = 45, message = "credit_card_brand must be between 1 and 45 characters")
    private String creditCardBrand;

    private CreditCardBrand brand;

    public CreateCreditCardRequest() {
    }

    public CreateCreditCardRequest(@NotNull(message = "customerId field must be sent.") @Size(min = 1, max = 45, message = "UUID must be an correct UUID.") UUID customerId, @NotNull(message = "lastFourDigits field must be sent.") @Size(min = 1, max = 255, message = "lastFourDigits must be between 1 and 255 characters") String creditCardNumber, @NotNull(message = "creditCardName field must be sent.") @Size(min = 1, max = 255, message = "creditCardName must be between 1 and 255 characters") String creditCardName, @NotNull(message = "expirationDate field must be sent.") @Size(min = 1, max = 255, message = "expirationDate must be between 1 and 255 characters") String expirationDate, @NotNull(message = "credit_card_brand field must be sent.") @Size(min = 1, max = 255, message = "credit_card_brand must be between 1 and 255 characters") String creditCardBrand) {
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardName = creditCardName;
        this.expirationDate = expirationDate;
        this.creditCardBrand = creditCardBrand;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreditCardBrand() {
        return creditCardBrand;
    }

    public void setCreditCardBrand(String creditCardBrand) {
        this.creditCardBrand = creditCardBrand;
    }

    public CreditCardBrand getBrand() {
        return brand;
    }

    public void setBrand(CreditCardBrand brand) {
        this.brand = brand;
    }

    public void build() {
        this.brand = CreditCardBrand.valueOf(this.creditCardBrand);
    }

    public static CreditCard convertToEntity(CreateCreditCardRequest request) {
        return mapper.map(request, CreditCard.class);
    }
}