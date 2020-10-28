package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.service.RateService;
import com.senac.assister.backend.domain.service.ServicesService;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import com.senac.assister.backend.rest.dto.service.ServiceRequest;
import com.senac.assister.backend.rest.dto.service.ServiceResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final ServicesService servicesService;
    private final RateService rateService;

    public ServiceController(ServicesService service, RateService rateService) {
        this.servicesService = service;
        this.rateService = rateService;
    }

    @PostMapping
    @ApiOperation("Create service")
    public ResponseEntity<ServiceResponse> createService(@Valid @RequestBody ServiceRequest serviceRequest) {
        Service service = ServiceRequest.convertToEntity(serviceRequest);
        service = servicesService.save(service);
        ServiceResponse serviceResponse = ServiceResponse.convertToResponse(service);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("Update service")
    public ResponseEntity<ServiceResponse> updateService(@Valid @RequestBody ServiceRequest serviceRequest){
        Service service = ServiceRequest.convertToEntity(serviceRequest);
        service = servicesService.update(service);
        ServiceResponse serviceResponse = ServiceResponse.convertToResponse(service);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping("/{id}/rate")
    @ApiOperation("Create rate in service")
    public ResponseEntity<ServiceResponse> createRateInService(@PathVariable UUID id, @Valid @RequestBody CreateRateRequest rateRequest){
        Service service = servicesService.findById(id);
        Rate rate =  CreateRateRequest.convertToEntity(rateRequest);
        rate = rateService.save(rate);
        service.setRate(rate);
        servicesService.update(service);
        return new ResponseEntity<>(ServiceResponse.convertToResponse(service), HttpStatus.OK);
    }

}
