package com.senac.assister.backend.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Can't find customer.")
public class CustomerNotFoundException extends RuntimeException {
}