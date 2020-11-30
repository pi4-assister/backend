package com.senac.assister.backend.rest.resource;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.service.RateService;
import com.senac.assister.backend.domain.service.ServicesService;
import com.senac.assister.backend.rest.dto.customer.CustomerSQtd;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import com.senac.assister.backend.rest.dto.rate.CreateRateResponse;
import com.senac.assister.backend.rest.dto.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
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

    @PostMapping("/quote")
    @ApiOperation("Quote assister service. Service will attach to customer sent on authentication.")
    public ResponseEntity<QuoteServiceResponse> createAndQuoteService(@RequestBody QuoteServiceRequest request) {
        Service service = servicesService.quoteService(QuoteServiceRequest.convertToEntity(request));

        QuoteServiceResponse response = QuoteServiceResponse.convertToDto(service);

        return new ResponseEntity<QuoteServiceResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/rate")
    @ApiOperation("Create rate in service")
    public ResponseEntity<CreateRateResponse> createRateInService(@PathVariable UUID id, @Valid @RequestBody CreateRateRequest rateRequest) {
        Service service = servicesService.findById(id);

        Rate rate = CreateRateRequest.convertToEntity(rateRequest);
        rate = rateService.save(rate);
        service.setRate(rate);

        servicesService.update(service);

        return new ResponseEntity<CreateRateResponse>(CreateRateResponse.convertToDto(rate), HttpStatus.OK);
    }

    @GetMapping("/assisters")
    @ApiOperation("List assisters in range date")
    public ResponseEntity<List<CustomerSQtd>> listAssisters(@RequestParam("dateI") Instant dateI,
                                                            @RequestParam("dateF") Instant dateF) {
        List<CustomerSQtd> list = servicesService.findAllAssisterInRange(dateI, dateF);
        return new ResponseEntity<List<CustomerSQtd>>(list, HttpStatus.OK);
    }

    @PostMapping("/finish/{id}")
    @ApiOperation("Finish Service")
    public ResponseEntity<ServiceResponse> finishService(@PathVariable UUID id) {
        Service request = new Service();
        request.setId(id);

        Service service = servicesService.finishService(request);

        ServiceResponse response = ServiceResponse.convertToResponse(service);

        return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/customer/accept/{id}")
    @ApiOperation("Customer accept service")
    public ResponseEntity<ServiceResponseAlterStatus> customerAcceptService(@PathVariable UUID id) {
        ServiceResponseAlterStatus service =
                ServiceResponseAlterStatus.
                        convertToResponse(
                                servicesService.alterStatusService(id, ServiceStatus.INITIATED)
                        );
        return new ResponseEntity<ServiceResponseAlterStatus>(service, HttpStatus.OK);
    }

    @PostMapping("/assister/accept/{id}")
    @ApiOperation("Assister accept service")
    public ResponseEntity<ServiceResponseAlterStatus> assisterAcceptService(@PathVariable UUID id) {
        ServiceResponseAlterStatus service =
                ServiceResponseAlterStatus
                        .convertToResponse(
                                servicesService.alterStatusService(id, ServiceStatus.IN_PROGRESS)
                        );
        servicesService.sendEmail(id);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get service per id")
    public ResponseEntity<Service> getService(@PathVariable UUID id) {
        return new ResponseEntity<Service>(servicesService.findById(id), HttpStatus.OK);
    }
}