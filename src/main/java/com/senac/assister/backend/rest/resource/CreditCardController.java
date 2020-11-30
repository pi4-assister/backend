package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.service.CreditCardService;
import com.senac.assister.backend.domain.service.CustomerService;
import com.senac.assister.backend.rest.dto.credit_card.CreateCreditCardRequest;
import com.senac.assister.backend.rest.dto.credit_card.CreateCreditCardResponse;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/api/v1/card")
public class CreditCardController {

    private final CustomerService customerService;
    private final CreditCardService creditCardService;

    public CreditCardController(CustomerService customerService, CreditCardService creditCardService) {
        this.customerService = customerService;
        this.creditCardService = creditCardService;
    }

    @ApiOperation("Create credit card")
    @PostMapping()
    public ResponseEntity<CreateCreditCardResponse> createCard(@Valid @RequestBody CreateCreditCardRequest createCreditCardRequest) {
        CreditCard card = creditCardService.save(CreateCreditCardRequest.convertToEntity(createCreditCardRequest));

        CreateCreditCardResponse response = CreateCreditCardResponse.convertToDto(card);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation("Delete credit card")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable UUID id) {
        creditCardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Get credit card by id")
    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getCreditCard(@PathVariable UUID id) {
        CreditCard card = creditCardService.findById(id);

        CreditCardResponse response = CreditCardResponse.convertToDto(card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}