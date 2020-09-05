package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.service.CustomerService;
import com.senac.assister.backend.rest.dto.customer.CustomerRequestDto;
import com.senac.assister.backend.rest.dto.customer.CustomerResponseDto;
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
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<CustomerResponseDto> response = customerService.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDto> createEntireCustomer(@Valid @RequestBody CustomerRequestDto createCustomerRequest) {

        createCustomerRequest.build();

        Customer customer = customerService.save(convertToEntity(createCustomerRequest));

        CustomerResponseDto response = convertToDto(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<CustomerResponseDto> editCustomer(@Valid @RequestBody CustomerRequestDto createCustomerRequest) {

        createCustomerRequest.build();

        Customer customer = customerService.update(convertToEntity(createCustomerRequest));

        CustomerResponseDto response = convertToDto(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> deleteCustomerById(@PathVariable UUID id) {
        CustomerResponseDto response = convertToDto(customerService.delete(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable UUID id) {
        CustomerResponseDto response = convertToDto(customerService.findById(id));

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    private CustomerResponseDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    private Customer convertToEntity(CustomerRequestDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}