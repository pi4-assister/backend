package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.service.CreditCardService;
import com.senac.assister.backend.domain.service.CustomerService;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponse;
import com.senac.assister.backend.rest.dto.customer.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CreditCardService creditCardService;

    public CustomerController(CustomerService customerService, CreditCardService creditCardService) {
        this.customerService = customerService;
        this.creditCardService = creditCardService;
    }

    @ApiOperation("List all customers.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns list of customers"),
    })
    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        List<CustomerResponse> response = customerService.findAll()
                .stream()
                .map(CustomerResponse::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Create customer from scratch.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created customer successfully.")
    })
    @PostMapping()
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        Customer customer = customerService.save(CreateCustomerRequest.convertToEntity(request));

        CreateCustomerResponse response = CreateCustomerResponse.convertToDto(customer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation("Edit customer.")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@Valid @RequestBody UpdateCustomerRequest request, @PathVariable UUID id) {
        request.setId(id);

        Customer customer = customerService.update(UpdateCustomerRequest.convertToEntity(request));

        CustomerResponse response = CustomerResponse.convertToDto(customer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Delete customer.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Find customer by id")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> show(@PathVariable UUID id) {
        CustomerResponse response = CustomerResponse.convertToDto(customerService.show(id));

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @ApiOperation("List all credit cards by customer id.")
    @GetMapping("/{id}/credit-cards")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardsByCustomer(@PathVariable UUID id) {
        List<CreditCardResponse> response = creditCardService.findAllByCustomerId(id)
                .stream()
                .map(CreditCardResponse::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("List active credit card by customer id.")
    @GetMapping("/{id}/credit-card")
    public ResponseEntity<CreditCardResponse> getActiveCreditCardByCustomerId(@PathVariable UUID id) {
        CreditCard card = creditCardService.findActiveCreditCard(id);

        CreditCardResponse response = CreditCardResponse.convertToDto(card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/profile-picture")
    public ResponseEntity<UploadPictureResponse> uploadImage(@RequestParam("profile-picture") MultipartFile file, @PathVariable UUID id) {
        String url = customerService.uploadProfilePicture(file, id);

        UploadPictureResponse response = new UploadPictureResponse(id, url);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation("Get password code")
    @PostMapping("/password-code")
    public ResponseEntity<Void> getPasswordCode(@RequestBody GetPasswordCodeRequest request) {
        customerService.generatePasswordCode(request.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Change password")
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request, @PathVariable UUID id) {
        customerService.changePassword(id, request.getPassword(), request.getCode());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}