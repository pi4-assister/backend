package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.service.CreditCardService;
import com.senac.assister.backend.rest.dto.credit_card.CreateCreditCardRequest;
import com.senac.assister.backend.rest.dto.credit_card.CreateCreditCardResponse;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseBody
@RequestMapping("/api/v1/card")
public class CreditCardController {

    private final ModelMapper modelMapper;

    private final CreditCardService creditCardService;

    public CreditCardController(ModelMapper modelMapper, CreditCardService creditCardService) {
        this.modelMapper = modelMapper;
        this.creditCardService = creditCardService;
    }

    @PostMapping()
    public ResponseEntity<CreateCreditCardResponse> createCard(@Valid @RequestBody CreateCreditCardRequest createCreditCardRequest) {
        createCreditCardRequest.build();

        CreditCard creditCard = creditCardService.save(convertToEntity(createCreditCardRequest));

        CreateCreditCardResponse response = convertToDto(creditCard);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private CreateCreditCardResponse convertToDto(CreditCard creditCard) {
        return modelMapper.map(creditCard, CreateCreditCardResponse.class);
    }

    private CreditCard convertToEntity(CreateCreditCardRequest creditCardDto) {
        return modelMapper.map(creditCardDto, CreditCard.class);
    }
}