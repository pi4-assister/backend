package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.security.MyUserDetails;
import com.senac.assister.backend.rest.dto.customer.CustomerResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/api/v1")
public class AuthController {

    @ApiOperation("Authorize user session")
    @GetMapping("/session/")
    public ResponseEntity<CustomerResponse> login() {
        // Spring security already authenticates the user and SecuriryContextHolder gets the logged user on session.
        Customer customer = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).loggedCustomer;

        CustomerResponse response = CustomerResponse.convertToDto(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}