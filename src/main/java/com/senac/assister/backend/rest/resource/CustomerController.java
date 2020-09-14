package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.service.CustomerService;
import com.senac.assister.backend.rest.dto.credit_card.CreditCardResponse;
import com.senac.assister.backend.rest.dto.customer.CreateCustomerRequest;
import com.senac.assister.backend.rest.dto.customer.CreateCustomerResponse;
import com.senac.assister.backend.rest.dto.customer.CustomerResponse;
import com.senac.assister.backend.rest.dto.customer.UpdateCustomerRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("List all customers.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns list of customers")
    })
    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> index() {
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
    @GetMapping("/{id}/creditCards")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardsByCustomer(@PathVariable UUID id) {
        List<CreditCardResponse> response = customerService.getAllCreditCardsByCustomer(id)
                .stream()
                .map(CreditCardResponse::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}