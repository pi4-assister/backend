package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.service.CreditCardService;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardRequestDto;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponseDto;
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
    public ResponseEntity<CreditCardResponseDto> createCard(@Valid @RequestBody CreditCardRequestDto createCreditCardRequest) {
        createCreditCardRequest.build();

        CreditCard creditCard = creditCardService.save(convertToEntity(createCreditCardRequest));

        CreditCardResponseDto response = convertToDto(creditCard);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private CreditCardResponseDto convertToDto(CreditCard creditCard) {
        return modelMapper.map(creditCard, CreditCardResponseDto.class);
    }

    private CreditCard convertToEntity(CreditCardRequestDto creditCardDto) {
        return modelMapper.map(creditCardDto, CreditCard.class);
    }
}